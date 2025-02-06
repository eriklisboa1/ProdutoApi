package io.github.eriklisboa1.produtosapi.contoller;

import io.github.eriklisboa1.produtosapi.model.Produto;
import io.github.eriklisboa1.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {


    private ProdutoRepository produtoRepository;

    //construntor cria ou inicia o objeto
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Salvando produto: " + produto);

        var id = UUID.randomUUID().toString(); //GERA UM HAS OU SEJA UM NUMERO ALEATORIO DIFERENTE
        produto.setId(id);
        
        produtoRepository.save(produto);
        return produto;
    }
    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String  id){
      //produto esta presente ? se estiver pegue o produto se nao retorne null
          return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String  id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") String id,
                          @RequestBody Produto produto) {

        produto.setId(id);// set utilizado para alteltar
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome){
        return produtoRepository.findByNome(nome);
    }
}
