package com.starkindustries.volleywithrecyclerview.Model
class User
{
    lateinit var profileName:String
    var profileImage:Int=0
    constructor(profileName_:String,profileImage_: Int)
    {
        this.profileName==profileName_
        this.profileImage=profileImage_
    }
    constructor(){

    }
}