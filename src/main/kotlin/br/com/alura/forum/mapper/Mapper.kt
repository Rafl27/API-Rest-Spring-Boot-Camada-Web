package br.com.alura.forum.mapper

interface Mapper<T,U> {

    //Converte de T para U
    fun map(t: T) : U

}