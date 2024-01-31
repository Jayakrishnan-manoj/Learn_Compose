package com.example.learn_compose

sealed class Destinations (val route: String){
    object firstScreen : Destinations("First Screen")
    object secondScreen : Destinations("Second Screen")
}