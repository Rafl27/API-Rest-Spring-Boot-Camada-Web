package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: MutableList<Topico> = ArrayList(),
    private val cursoService: CursoService,
    private val usuarioService: AutorService,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
) {

//    init {
//        val topico1 = Topico(
//            id = 1,
//            titulo = "Duvida Kotlin",
//            mensagem = "muito hard isso aqui",
//            curso = Curso(
//                id = 2,
//                nome = "Kotlin",
//                categoria = "Programação"
//            ),
//            autor = Usuario(
//                id = 1,
//                nome = "Rafael",
//                email = "rafl@gmail.com"
//            ),
//        )
//        topicos = Arrays.asList(topico1)
//    }

    //DTOs que são usados apenas para visualização por um usuário, como listar abaixo, podem ser chamados de views. Isso é uma boa prática.
    fun listar(): List<TopicoView> {
        return topicos.stream().map { topico ->
            topicoViewMapper.map(topico)
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        //returns the first topicos that matches the given id. (Ids are unique, so it's not a problem to find only one)
        val topico = topicos.first { it.id == id }
        return topicoViewMapper.map(topico)
    }

    fun buscarPorIdCompleto(id: Long): Topico {
        //returns the first topicos that matches the given id. (Ids are unique, so it's not a problem to find only one)
        val topico = topicos.first { it.id == id }
        return topico
    }

    fun verificaSeTopicoExiste(id: Long): Boolean {
        return topicos.any { it.id == id }
    }

//    fun cadastrar(topico: Topico): Topico {
//        topicos.add(topico)
//        println("Tópico: ${topico.titulo} adicionado com sucesso!")
//        return topicos.first { it.id == topico.id }
//    }

    //post por meio de um DTO
    fun cadastrar(dto: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico).toMutableList()
        println(
            "Tópico: ${dto.titulo} adicionado com sucesso no curso ${cursoService.buscarPorId(dto.idCurso).nome} / Submitted by: ${
                usuarioService.buscarPorId(
                    dto.idAutor
                ).nome
            }"
        )
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): String {
        val tituloAntigo = buscarPorIdCompleto(form.id).titulo
        val mensagemAntiga = buscarPorIdCompleto(form.id).mensagem
        buscarPorIdCompleto(form.id).titulo = form.titulo
        buscarPorIdCompleto(form.id).mensagem = form.mensagem
        return "Tópico: $tituloAntigo atualizado com sucesso para ${form.titulo} \n Mensagem: $mensagemAntiga atualizada com sucesso para ${form.mensagem}"
    }

    fun delete(id: Long):String {
        val nomeTopico = buscarPorId(id).titulo
        val topico = buscarPorIdCompleto(id)
        topicos.remove(topico)
        return "Tópico: $nomeTopico eliminado com sucesso."
    }

    fun retornaRespostas(id : Long): List<Resposta> {
        return buscarPorIdCompleto(id).respostas
    }

    fun getTopicosArraySize () : Int {
        return topicos.size
    }

}