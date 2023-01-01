package br.com.alura.forum.service

import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoService (private  var topicos : MutableList<Topico> = ArrayList(),
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
//                id= 2,
//                nome = "Kotlin",
//                categoria = "Programação"),
//            autor = Usuario(
//                id = 1,
//                nome = "Rafael",
//                email = "rafl@gmail.com"
//            ),
//        )
//        val topico2 = Topico(
//            id = 2,
//            titulo = "Duvida Kotlin1",
//            mensagem = "muito hard isso aqui",
//            curso = Curso(
//                id= 2,
//                nome = "Kotlin",
//                categoria = "Programação"),
//            autor = Usuario(
//                id = 1,
//                nome = "Rafael",
//                email = "rafl@gmail.com"
//            ),
//        )
//        val topico3 = Topico(
//            id = 3,
//            titulo = "Duvida Kotlin2",
//            mensagem = "muito hard isso aqui",
//            curso = Curso(
//                id= 2,
//                nome = "Kotlin",
//                categoria = "Programação"),
//            autor = Usuario(
//                id = 1,
//                nome = "Rafael",
//                email = "rafl@gmail.com"
//            ),
//        )
//        topicos =  Arrays.asList(topico1, topico2, topico3)
//    }

    //DTOs que são usados apenas para visualização por um usuário, como listar abaixo, podem ser chamados de views. Isso é uma boa prática.
    fun listar(): List<TopicoView> {
     return topicos.stream().map{
             topico -> topicoViewMapper.map(topico)
     }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        //returns the first topicos that matches the given id. (Ids are unique, so it's not a problem to find only one)
        val topico = topicos.first { it.id == id }
        return topicoViewMapper.map(topico)
    }

//    fun cadastrar(topico: Topico): Topico {
//        topicos.add(topico)
//        println("Tópico: ${topico.titulo} adicionado com sucesso!")
//        return topicos.first { it.id == topico.id }
//    }

    //post por meio de um DTO
    fun cadastrar(dto: NovoTopicoForm) {
        val topico = topicoFormMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico).toMutableList()
        println("Tópico: ${dto.titulo} adicionado com sucesso no curso ${cursoService.buscarPorId(dto.idCurso).nome} / Submitted by: ${usuarioService.buscarPorId(dto.idAutor).nome}")

    }

}