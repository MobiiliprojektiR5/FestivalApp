package com.example.loginandsignup.models;

class ModelPdf {

    //variables
    var uid:String = ""
    var dateend:String = ""
    var datestart:String = ""
    var imageurl:String = ""
    var infotext:String = ""
    var locationaddress:String = ""
    var locationtown:String = ""
    var name:String = ""
    var ticket:String = ""
    var webpage:String = ""
    var isFavorite = false

    //empty constructor (required by firebase)
    constructor()

    //parameterized constructor
    constructor(
        uid: String,
        dateend: String,
        datestart: String,
        imageurl: String,
        infotext: String,
        locationaddress: String,
        locationtown: String,
        name: String,
        ticket: String,
        webpage: String,
        isFavorite: Boolean
    ) {
        this.uid = uid
        this.dateend = dateend
        this.datestart = datestart
        this.imageurl = imageurl
        this.infotext = infotext
        this.locationaddress = locationaddress
        this.locationtown = locationtown
        this.name = name
        this.ticket = ticket
        this.webpage = webpage
        this.isFavorite = isFavorite
    }
}
