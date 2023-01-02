package br.com.alura.forum.service

import br.com.alura.forum.dto.RespostaForm
import br.com.alura.forum.model.Resposta
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import java.util.*

@Service
class RespostaService(
    private var respostas: MutableList<Resposta> = ArrayList(),
    private val cursoService: CursoService,
    private val autorService: AutorService,
    private val topicoService: TopicoService
) {
    //add a new answer.
    fun addResposta(resposta: RespostaForm): String {
        if(topicoService.buscarPorIdCompleto(resposta.topicoId))
        topicoService.buscarPorIdCompleto(resposta.topicoId).respostas.plus(
            Resposta(
                id = respostas.size + 1.toLong(),
                mensagem = resposta.mensagem,
                autor = autorService.buscarPorId(resposta.idAutor),
                topico = topicoService.buscarPorId(resposta.topicoId)
            )
        )
        return "Resposta ${resposta.mensagem} adicionada com sucesso ao tópico ${topicoService.buscarPorIdCompleto(resposta.topicoId).titulo} por ${autorService.buscarPorId(resposta.idAutor).nome} - ${autorService.buscarPorId(resposta.idAutor).email}"
    }
}