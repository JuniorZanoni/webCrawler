import Email.SendEmail
import Models.ModelEmails
import WebCrawler.WebCrawler

class Main {
    static void main(String[] args) {
        new WebCrawler().getTabelaErrosPadraoTISS()
        new WebCrawler().getHistoricoVersoesComponentesTISS()
        new WebCrawler().getTabelaErrosEnvioANS()

        List<String> emails = new ModelEmails().getAll()
        new SendEmail().sendMultiplesEmails(emails)
    }
}
