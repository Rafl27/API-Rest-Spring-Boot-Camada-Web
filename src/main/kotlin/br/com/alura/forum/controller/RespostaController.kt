package br.com.alura.forum.controller

import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.service.RespostaService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/topicos/respostas")
class RespostaController(private val respostaService: RespostaService) {
    @GetMapping
    fun hellodude(): String {
        return "hey man"
    }

    //    @PostMapping
//    fun addResposta(@RequestBody @Valid resposta:RespostaForm){
//        respostaService.addResposta(resposta)
//    }
    @PostMapping
    fun addResposta(@RequestBody resposta: RespostaForm) {
        respostaService.addResposta(resposta)
    }
}