
Feature: EES e-Fatura
  @InvoiceCreation
  Scenario: EES e-Fatura belgesi olusturma
    Given Entegratorluk portal giris yapılır
    When ePosta ve Sifre alani doldurulur
    When Giris Yap butonuna tiklanir
    When Firma secimi yapilir
    When Devam butonuna tiklanir
    When Turu Kapat tiklanir
    When Fatura Olusturma butonuna tiklanir
    When Yeni Ekle butonuna tiklanir
    When VKN TCKN bilgisi eklenir
    When Unvan bilgisi eklenir
    When Mal Hizmet eklenir
    When Kaydet butonuna tiklanir
    When Fatura Numara eklenip aliciya gonderilir
    When Giden Faturalar ekranina girilir
    When Olusturulan belgenin durumu kontrol edilir