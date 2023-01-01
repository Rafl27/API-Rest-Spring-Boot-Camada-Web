package br.com.alura.forum.controller

import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.service.TopicoService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/topicos")
class TopicoController (private val service : TopicoService){

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.listar();
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
    @PostMapping
    fun cadastrar (@RequestBody dto: NovoTopicoForm) {
        service.cadastrar(dto);
    }

}