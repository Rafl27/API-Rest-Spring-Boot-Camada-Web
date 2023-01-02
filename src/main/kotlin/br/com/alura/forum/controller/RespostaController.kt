package br.com.alura.forum.controller

import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.model.Resposta
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
    @ResponseBody
    fun addResposta(@RequestBody resposta: RespostaForm): String {
//        respostaService.addResposta(resposta)
//        val resp =
        return respostaService.addResposta(resposta)
    }

    @DeleteMapping("/{idTopico}/{idAutor}/{idResposta}")
    @ResponseBody
    fun deleteResposta(
        @PathVariable idResposta: Long,
        @PathVariable idAutor: Long,
        @PathVariable idTopico: Long
    ) {
        respostaService.deleteResposta(idResposta, idAutor, idTopico)
        println("chegou 😎👍🏻")
    }

    @PutMapping("/{idTopico}/{idAutor}/{idResposta}")
    fun updateResposta(
        @PathVariable idResposta: Long,
        @PathVariable idAutor: Long,
        @PathVariable idTopico: Long,
        @RequestBody mensagem: String
    ) {
        respostaService.editarResposta(idResposta, idAutor, idTopico, mensagem)
    }
}