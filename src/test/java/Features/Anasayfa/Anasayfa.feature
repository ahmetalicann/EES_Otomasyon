

Feature: EES Giris Yapma
  @LoginTest @IdmLogin
  Scenario: EES IDM ile Giris Yapma
    Given Entegratorluk portal giris yapılır
    When ePosta ve Sifre alani doldurulur
    When Giris Yap butonuna tiklanir
    When Firma secimi yapilir
    When Devam butonuna tiklanir
    When Turu Kapat tiklanir

