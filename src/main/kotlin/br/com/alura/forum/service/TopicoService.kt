package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService (private  var topicos : List<Topico>){

    init {
        val topico1 = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "muito hard isso aqui",
            curso = Curso(
                id= 2,
                nome = "Kotlin",
                categoria = "Programação"),
            autor = Usuario(
                id = 1,
                nome = "Rafael",
                email = "rafl@gmail.com"
            ),
        )
        val topico2 = Topico(
            id = 2,
            titulo = "Duvida Kotlin1",
            mensagem = "muito hard isso aqui",
            curso = Curso(
                id= 2,
                nome = "Kotlin",
                categoria = "Programação"),
            autor = Usuario(
                id = 1,
                nome = "Rafael",
                email = "rafl@gmail.com"
            ),
        )
        val topico3 = Topico(
            id = 3,
            titulo = "Duvida Kotlin2",
            mensagem = "muito hard isso aqui",
            curso = Curso(
                id= 2,
                nome = "Kotlin",
                categoria = "Programação"),
            autor = Usuario(
                id = 1,
                nome = "Rafael",
                email = "rafl@gmail.com"
            ),
        )
        topicos =  Arrays.asList(topico1, topico2, topico3)
    }
    fun listar(): List<Topico> {
     return topicos;
    }

    fun buscarPorId(id: Long): Topico {
        //returns the first topicos that matches the given id. (Ids are unique, so it's not a problem to find only one)
        return topicos.first { it.id == id }
    }

}