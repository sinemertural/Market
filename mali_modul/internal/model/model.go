package model

type HareketTipi string

const (
	Gelir HareketTipi = "gelir"
	Gider HareketTipi = "gider"
)

type Hareket struct {
	ID          int         `json:"id"`
	Tarih       string      `json:"tarih"`
	Aciklama    string      `json:"aciklama"`
	Tutar       float64     `json:"tutar"`
	HareketTipi HareketTipi `json:"hareket_tipi"` // "gelir" veya "gider"
}

type MaliDurum struct {
	ToplamGelir float64 `json:"toplam_gelir"`
	ToplamGider float64 `json:"toplam_gider"`
	NetKar      float64 `json:"net_kar"`
}

// HareketRepository interface'i veri işlemleri için
type HareketRepository interface {
	GetAll() []Hareket
	GetByID(id int) (*Hareket, error)
	Create(hareket *Hareket) error
	Delete(id int) error
	GetMaliDurum() MaliDurum
}
