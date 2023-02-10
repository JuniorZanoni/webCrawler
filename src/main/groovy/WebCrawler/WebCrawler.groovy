package WebCrawler

import com.opencsv.CSVWriter
import groovyx.net.http.HttpBuilder
import groovyx.net.http.optional.Download
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class WebCrawler {

    void getTabelaErrosPadraoTISS() {
        Document page = getPage(linkPageTISS())
        Element content = page.getElementsByClass("internal-link").get(0)
        String link = content.getElementsByTag("a").attr("href")

        Document page4 = getPage(link)
        Element table = page4.getElementsByTag("tbody").first().getElementsByTag("tr").last()
        link = table.lastElementChild().firstElementChild().attr("href")

        donwloadAndSaveInFolderDownloads(link, "padrao-de-comunicao-TISS.zip")
    }

    void getHistoricoVersoesComponentesTISS() {
        Document page = getPage(linkPageTISS())
        Element content = page.getElementsByClass("internal-link").get(1)
        String link = content.getElementsByTag("a").attr("href")

        Document page4 = getPage(link)
        Element content4 = page4.getElementsByTag("tbody").first()
        List<Element> listTr = content4.getElementsByTag("tr")

        List informations = []

        listTr.forEach {tr ->
            List<Element> listTd = tr.getElementsByTag("td")
            String competencia =  listTd.get(0).text()

            List<String> competenciaSplit = competencia.split("/")
            Integer year = Integer.parseInt(competenciaSplit[1])

            if(year >= 2016) {
                String publicacao = listTd.get(1).text()
                String inicioVigencia = listTd.get(2).text()
                informations.add([competencia, publicacao, inicioVigencia])
            }
        }

        createCSCInFolderDownloads(informations, "./Downloads/historico-versoes-de-componentes-TISS.csv")
    }

    void getTabelaErrosEnvioANS() {
        Document page = getPage(linkPageTISS())
        Element content = page.getElementsByClass("internal-link").get(2)
        String link = content.getElementsByTag("a").attr("href")

        Document page4 = getPage(link)
        Element content4 = page4.getElementById("parent-fieldname-text")
        Element element = content4.getElementsByTag("a").get(0)
        link = element.attr("href")

        donwloadAndSaveInFolderDownloads(link, "tabela-erros-de-envio-ANS.xlsx")
    }

    private String linkPageTISS() {
        Document page1 = getPage("https://www.gov.br/ans/pt-br")
        Element content1 = page1.getElementById("ce89116a-3e62-47ac-9325-ebec8ea95473")
        String link1 = content1.getElementsByTag("a").attr("href")

        Document page2 = getPage(link1)
        Element content2 = page2.getElementsByClass("govbr-card-content").first()
        return content2.getElementsByTag("a").attr("href")
    }

    private Document getPage(String link) {
        return (Document) HttpBuilder.configure { request.uri = link }.get()
    }

    private donwloadAndSaveInFolderDownloads(String link, String nameFile) {

        new File("./Downloads").mkdirs()
        File path = new File("./Downloads/${nameFile}")

        HttpBuilder.configure {
            request.uri = link
        }.get { Download.toFile(delegate, path)}
    }

    private void createCSCInFolderDownloads(List informations, String filePath)
    {
        File file = new File(filePath);
        try {
            new File("./Downloads").mkdirs()

            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            writer.writeNext((String [])["competencia", "publicacao", "inicioVigencia"]);
            informations.forEach {information -> writer.writeNext((String[]) information);}

            writer.close();
        }
        catch (IOException e) {
            e.message();
        }
    }
}
