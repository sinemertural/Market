package main

import (
	"log"
	"mali-modul/internal/handler"
	"mali-modul/internal/service"

	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/logger"
)

func main() {
	// JSON Service oluştur
	repo, err := service.NewJsonService("hareketler.json")
	if err != nil {
		log.Fatalf("Repository oluşturulurken hata: %v", err)
	}

	// Handler oluştur
	hareketHandler := handler.NewHareketHandler(repo)

	// Fiber uygulamasını oluştur
	app := fiber.New(fiber.Config{
		AppName: "Mali Modül API",
	})

	// Middleware
	app.Use(logger.New())

	// API rotaları
	api := app.Group("/api")

	// Mali hareket rotaları
	hareketler := api.Group("/hareketler")
	hareketler.Get("/", hareketHandler.GetHareketler)
	hareketler.Get("/:id", hareketHandler.GetHareket)
	hareketler.Post("/", hareketHandler.CreateHareket)
	hareketler.Delete("/:id", hareketHandler.DeleteHareket)

	// Mali durum rotası
	api.Get("/mali-durum", hareketHandler.GetMaliDurum)

	// Sunucuyu başlat
	log.Printf("Sunucu http://localhost:8080 adresinde başlatılıyor...")
	log.Fatal(app.Listen(":8080"))
}
