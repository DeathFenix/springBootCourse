package br.com.springcourse;

import br.com.springcourse.domain.entity.Cidade;
import br.com.springcourse.domain.repository.CidadeRepository;
import br.com.springcourse.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

    @Autowired
    private CidadeService service;

    @Override
    public void run(String... args) throws Exception {
        //System.out.println("Inicializado!!!");
        //salvarCidade();
        //listarCidades();
        //service.listarCidadesPorNome();
        //service.listarCidadesPorHabitantes();
        //var cidade = new Cidade(null, "porto", null);
        //service.filtroDinamico(cidade).forEach(System.out::println);
        //service.listarCidadesByNomeSpec();
        //var cidade = new Cidade(1L, "São Paulo", 1000L);
        //service.listarCidadesSpecsFiltroDinamico(cidade);
        //service.listarCidadesPorNomeSQL();
        service.listarCidadesPorNomeSQL2();
    }



    public static void main(String[] args){
        SpringApplication.run(LocalizacaoApplication.class,args);
    }

}
