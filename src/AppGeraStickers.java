import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AppGeraStickers {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão http e buscar os top 250 filmes. Url alternativa da imdb
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url = "https://minhas-linguagens-api.herokuapp.com/linguagens";
        //utilizando ExtratorDeConteudoDoIMDB para teste. Criar clase para linguagens
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        
        var http = new ClientHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("Gerando stickers da imagem: "+ conteudo.getTitulo());
            System.out.println();

        }
    }
}
