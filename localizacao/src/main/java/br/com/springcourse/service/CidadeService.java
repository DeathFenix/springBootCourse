package br.com.springcourse.service;

import br.com.springcourse.domain.entity.Cidade;
import br.com.springcourse.domain.repository.CidadeRepository;
import static br.com.springcourse.domain.repository.spec.CidadeSpec.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    void salvarCidade(){
        var cidade = new Cidade(1L, "São Paulo", 12396372L);
        repository.save(cidade);
    }

    void listarCidades(){
        repository.findAll().forEach(System.out::println);
    }

    public void listarCidadesPorNomeSQL(){
        repository
                .findByNomeSqlNativo("São Paulo")
                .forEach(System.out::println);
    }

    //Projections
    public void listarCidadesPorNomeSQL2(){
        repository
                .findByNomeSqlNativo("São Paulo")
                .stream().map(cidadeProjections ->
                        new Cidade(cidadeProjections.getId(), cidadeProjections.getNome(), null))
                .forEach(System.out::println);
    }

    public void listarCidadesPorNome(){
        //cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
        //cidadeRepository.findByNomeStartingWith("Porto").forEach(System.out::println);
        //cidadeRepository.findByNomeEndingWith("a").forEach(System.out::println);
        //cidadeRepository.findByNomeContaining("za").forEach(System.out::println);
        //repository.findByNomeLike("porto%").forEach(System.out::println);
        //repository.findByNomeLike("porto%", Sort.by("habitantes") ).forEach(System.out::println);
        Pageable pageable = PageRequest.of(0, 10);
        repository
                .findByNomeLike("porto%", pageable )
                .forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes(){
        //cidadeRepository.findByHabitantes(6357984L).forEach(System.out::println);
        //cidadeRepository.findByHabitantesLessThan(1000001L).forEach(System.out::println);
        //cidadeRepository.findByHabitantesGreaterThan(1000000L).forEach(System.out::println);
        //cidadeRepository.findByHabitantesLessThanEqual(1000000L).forEach(System.out::println);
        repository
                .findByHabitantesLessThanAndNomeLike(1000001L, "Bra%")
                .forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return repository.findAll(example);
    }

    public void listarCidadesByNomeSpec(){
        repository
                .findAll(nomeEqual("São Paulo").and(habitantesGreaterThan(1000000L)))
                .forEach(System.out::println);
    }

    //  ******  Estudar   CriteriaBuilder API + Specifications (ajuda a não usar SQL no código )
    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        //select * from cidade where 1 = 1

        if (filtro.getId() != null){
            specs = specs.and( idEqual(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getNome())){
            specs = specs.and(nomeLike(filtro.getNome()));
        }

        if (filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);

    }
}
