package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {


    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {

        if(id == null){
            throw new IDNaoInformadoException();
        }

       
            String b = "/tmp/";
            String d = null;
            int i = 0;    
            
            if(id % 1000 == 0){
                i = id/1000; 
                
            }else{
                i = (id/1000) +1;                
            }    
            d = b+i;   
        
        return new CaminhoArquivo(Paths.get(d), Paths.get(d + "/" + id));   

        


    }

}
