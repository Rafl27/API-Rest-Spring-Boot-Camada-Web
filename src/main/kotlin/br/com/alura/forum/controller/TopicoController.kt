package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.io.Serializable
import java.util.*

@RestController
@RequestMapping("/topicos")
class TopicoController (private val service : TopicoService){

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar();
    }

    @GetMapping("/completo/{id}")
    fun listarTopicoCompleto(@PathVariable id:Long): Topico {
        return service.buscarPorIdCompleto(id);
    }

    //anything between curly brackets means that it's a dynamic parameter.
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView{
        return service.buscarPorId(id);
    }

    //esse tipo de post precisa de todas as properties que existem dentro de um tópico
//    {
//        "id":1,
//        "titulo": "react doesn't work",
//        "mensagem": "n funfa nem por reza",
//        "curso":{
//        "id": 1,
//        "nome": "front-end",
//        "categoria": "programacao"
//    },
//        "autor": {
//        "id": "1",
//        "nome": "rafinha mil grau",
//        "email": "rafmil@gmail.com"
//    }
//    }
    //o ideal seria utilizar um dto, onde ao invés de criar um autor e um curso, onde seria passado apenas o id do curso e do autor.

//    @PostMapping
//    fun cadastrar (@RequestBody topico: Topico) {
//        service.cadastrar(topico);
//    }


    //Post utilizando DTO
    //By using @Valid, Spring will check the validations(bean validations) that are present in the NovoTopicoForm DTO, like NotEmpty, Size, and notNull.
    @PostMapping
    fun cadastrar (@RequestBody @Valid dto: NovoTopicoForm, uriBuilder: UriComponentsBuilder): ResponseEntity<TopicoView> {
        val topicoView = service.cadastrar(dto);
        val uri = uriBuilder.path("/topicos/${topicoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoView);

    }

    @PutMapping
    @ResponseBody
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): String {
        return service.atualizar(form);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    //Como é um delete o correto é retornar o status 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long): Unit {
        return service.delete(id);
    }

}