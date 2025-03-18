package service

import (
	"encoding/json"
	"errors"
	"mali-modul/internal/model"
	"os"
	"sync"
)

type JsonData struct {
	Hareketler []model.Hareket `json:"hareketler"`
	MaliDurum  model.MaliDurum `json:"mali_durum"`
}

type JsonService struct {
	filename string
	data     JsonData
	mu       sync.RWMutex
}

func NewJsonService(filename string) (*JsonService, error) {
	repo := &JsonService{
		filename: filename,
	}

	if err := repo.load(); err != nil {
		return nil, err
	}

	return repo, nil
}

func (r *JsonService) load() error {
	r.mu.Lock()
	defer r.mu.Unlock()

	data, err := os.ReadFile(r.filename)
	if err != nil {
		if os.IsNotExist(err) {
			// Dosya yoksa boş bir veri yapısı oluştur
			r.data = JsonData{
				Hareketler: make([]model.Hareket, 0),
				MaliDurum:  model.MaliDurum{},
			}
			return r.save()
		}
		return err
	}

	return json.Unmarshal(data, &r.data)
}

func (r *JsonService) save() error {
	data, err := json.MarshalIndent(r.data, "", "    ")
	if err != nil {
		return err
	}

	return os.WriteFile(r.filename, data, 0644)
}

func (r *JsonService) GetAll() []model.Hareket {
	r.mu.RLock()
	defer r.mu.RUnlock()
	return r.data.Hareketler
}

func (r *JsonService) GetByID(id int) (*model.Hareket, error) {
	r.mu.RLock()
	defer r.mu.RUnlock()

	for _, h := range r.data.Hareketler {
		if h.ID == id {
			return &h, nil
		}
	}
	return nil, errors.New("hareket bulunamadı")
}

func (r *JsonService) Create(hareket *model.Hareket) error {
	r.mu.Lock()
	defer r.mu.Unlock()

	// ID'yi otomatik ayarla
	if len(r.data.Hareketler) > 0 {
		lastHareket := r.data.Hareketler[len(r.data.Hareketler)-1]
		hareket.ID = lastHareket.ID + 1
	} else {
		hareket.ID = 1
	}

	// Mali durumu güncelle
	if hareket.HareketTipi == model.Gelir {
		r.data.MaliDurum.ToplamGelir += hareket.Tutar
	} else if hareket.HareketTipi == model.Gider {
		r.data.MaliDurum.ToplamGider += hareket.Tutar
	} else {
		return errors.New("geçersiz hareket tipi")
	}

	r.data.MaliDurum.NetKar = r.data.MaliDurum.ToplamGelir - r.data.MaliDurum.ToplamGider
	r.data.Hareketler = append(r.data.Hareketler, *hareket)

	return r.save()
}

func (r *JsonService) Delete(id int) error {
	r.mu.Lock()
	defer r.mu.Unlock()

	for i, h := range r.data.Hareketler {
		if h.ID == id {
			// Mali durumu güncelle
			if h.HareketTipi == model.Gelir {
				r.data.MaliDurum.ToplamGelir -= h.Tutar
			} else if h.HareketTipi == model.Gider {
				r.data.MaliDurum.ToplamGider -= h.Tutar
			}
			r.data.MaliDurum.NetKar = r.data.MaliDurum.ToplamGelir - r.data.MaliDurum.ToplamGider

			// Hareketi sil
			r.data.Hareketler = append(r.data.Hareketler[:i], r.data.Hareketler[i+1:]...)
			return r.save()
		}
	}
	return errors.New("hareket bulunamadı")
}

func (r *JsonService) GetMaliDurum() model.MaliDurum {
	r.mu.RLock()
	defer r.mu.RUnlock()
	return r.data.MaliDurum
}
