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
        if (topicoService.verificaSeTopicoExiste(resposta.topicoId)) {
            topicoService.buscarPorIdCompleto(resposta.topicoId).respostas.add(
                Resposta(
                    id = topicoService.buscarPorIdCompleto(resposta.topicoId).respostas.size + 1.toLong(),
                    mensagem = resposta.mensagem,
                    autor = autorService.buscarPorId(resposta.idAutor),
                    topico = topicoService.buscarPorId(resposta.topicoId)
                )
            )
            println(
                "Resposta ${resposta.mensagem} adicionada com sucesso ao tópico ${
                    topicoService.buscarPorIdCompleto(
                        resposta.topicoId
                    ).titulo
                } por ${autorService.buscarPorId(resposta.idAutor).nome} - ${autorService.buscarPorId(resposta.idAutor).email}"
            )
            println("${topicoService.buscarPorIdCompleto(resposta.topicoId).respostas.size}")

            return "Resposta ${resposta.mensagem} adicionada com sucesso ao tópico ${
                topicoService.buscarPorIdCompleto(
                    resposta.topicoId
                ).titulo
            } por ${autorService.buscarPorId(resposta.idAutor).nome} - ${autorService.buscarPorId(resposta.idAutor).email}"


        } else {
            println("Este tópico ainda não existe.")
            return "Este tópico ainda não existe."
        }
    }

    fun editarResposta(idResposta: Long, idTopico: Long, idAutor: Long, novaMensagem: String) : String {
        if (topicoService.verificaSeTopicoExiste(idTopico)) {
            if (topicoService.buscarPorIdCompleto(idTopico).autor.id == idAutor) {
                if (topicoService.retornaRespostas(idTopico).isNotEmpty()) {
                    val resposta = topicoService.buscarPorIdCompleto(idTopico).respostas.first{it.id == idResposta}
                    resposta.mensagem = novaMensagem;
                    return "Resposta ID($idResposta) alterada com sucesso."
                } else {
                    return "Este tópico ainda não tem respostas, portanto nada pode ser modificado."
                }
            } else {
                return "Você não é o criador original desta resposta."
            }
        } else {
            return "Este tópico ainda não existe, portanto não pode ser excluído."
        }
    }

    fun deleteResposta(idResposta: Long, idAutor: Long, idTopico: Long): String {
        if (topicoService.verificaSeTopicoExiste(idTopico)) {
            if (topicoService.buscarPorIdCompleto(idTopico).autor.id == idAutor) {
                if (topicoService.retornaRespostas(idTopico).isNotEmpty()) {
                    topicoService.buscarPorIdCompleto(idTopico).respostas.removeIf { it.id == idResposta  }
                    return "Resposta ID($idResposta) removida com sucesso."
                } else {
                    return "Este tópico ainda não tem respostas, portanto nada pode ser modificado."
                }
            } else {
                return "Você não é o criador original desta resposta."
            }
        } else {
            return "Este tópico ainda não existe, portanto não pode ser excluído."
        }
    }
}