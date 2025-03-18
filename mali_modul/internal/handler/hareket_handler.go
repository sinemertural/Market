package handler

import (
	"fmt"
	"mali-modul/internal/model"
	"strconv"

	"github.com/gofiber/fiber/v2"
)

type HareketHandler struct {
	repo model.HareketRepository
}

func NewHareketHandler(repo model.HareketRepository) *HareketHandler {
	return &HareketHandler{repo: repo}
}

func (p *HareketHandler) GetHareketler(c *fiber.Ctx) error {
	hareketler := p.repo.GetAll()
	fmt.Println("db: ", hareketler)
	return c.JSON(hareketler)
}

func (p *HareketHandler) GetHareket(c *fiber.Ctx) error {
	id, err := strconv.Atoi(c.Params("id"))
	if err != nil {
		return c.Status(fiber.StatusBadRequest).JSON(fiber.Map{
			"error": "Geçersiz ID formatı",
		})
	}

	hareket, err := p.repo.GetByID(id)
	if err != nil {
		return c.Status(fiber.StatusNotFound).JSON(fiber.Map{
			"error": "Hareket bulunamadı",
		})
	}

	return c.JSON(hareket)
}

func (p *HareketHandler) CreateHareket(c *fiber.Ctx) error {
	hareket := new(model.Hareket)
	if err := c.BodyParser(hareket); err != nil {
		return c.Status(fiber.StatusBadRequest).JSON(fiber.Map{
			"error": "İstek gövdesi geçersiz",
		})
	}

	if hareket.HareketTipi != "gelir" && hareket.HareketTipi != "gider" {
		return c.Status(fiber.StatusBadRequest).JSON(fiber.Map{
			"error": "Hareket tipi 'gelir' veya 'gider' olmalıdır",
		})
	}

	if err := p.repo.Create(hareket); err != nil {
		return c.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
			"error": err.Error(),
		})
	}

	return c.Status(fiber.StatusCreated).JSON(hareket)
}

func (p *HareketHandler) DeleteHareket(c *fiber.Ctx) error {
	id, err := strconv.Atoi(c.Params("id"))
	if err != nil {
		return c.Status(fiber.StatusBadRequest).JSON(fiber.Map{
			"error": "Geçersiz ID formatı",
		})
	}

	if err := p.repo.Delete(id); err != nil {
		return c.Status(fiber.StatusNotFound).JSON(fiber.Map{
			"error": "Hareket bulunamadı",
		})
	}

	return c.SendStatus(fiber.StatusNoContent)
}

func (p *HareketHandler) GetMaliDurum(c *fiber.Ctx) error {
	maliDurum := p.repo.GetMaliDurum()
	return c.JSON(maliDurum)
}
