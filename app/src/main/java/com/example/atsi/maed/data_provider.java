package com.example.atsi.maed;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class data_provider
{
    public static HashMap<String,List<String>> get_info()
    {
        HashMap<String,List<String>> hotelDetails=new HashMap<String,List<String>>();
        List<String> hotels=new ArrayList<String>();

        hotels.add("It is quite straightforward ");
        hotels.add(" Simply sign up then all the great restaurants and hotels available will be displayed.");
        hotels.add("Choose the restaurant that tickles your fancy, put together your order, enter your address ");
        hotels.add("and that is it you will get your food as soon as possible.");


        List<String> Romantic_movies=new ArrayList<String>();
        Romantic_movies.add("We deliver any time every day.");
        //Romantic_movies.add("abcs2652565");

        List<String> Horor_movies=new ArrayList<String>();
        Horor_movies.add("The minimum order value varies depending on the restaurant you order from. It is typically 30Birr");
        /*List<String> Comady_movies=new ArrayList<String>();
        Comady_movies.add("vghv");
        Comady_movies.add("vghv555");*/

        //hotelDetails.put("Action Movies",Action_movies);
        hotelDetails.put("What times can I order for?",Romantic_movies);

        hotelDetails.put("How does mead work?",hotels);
        hotelDetails.put("Is there a minimum order value?",Horor_movies);
        return hotelDetails;



    }
}
