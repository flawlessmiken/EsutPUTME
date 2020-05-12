package com.honmichael.esutputme.utils;

import android.net.Uri;

import com.honmichael.esutputme.R;
import com.honmichael.esutputme.model.HomeItemModel;
import com.honmichael.esutputme.model.Subjects;

public class ItemPlates {

    public Subjects[] subjects = {
            english, maths, current_affairs, biology, chemistry, commerce, economics, region, geology, government, history, literature, physics
    };

    public static final Subjects english = new Subjects("English", true);
    public static final Subjects maths = new Subjects("Mathematics", true);
    public static final Subjects current_affairs = new Subjects("Current Affairs", false);
    public static final Subjects biology = new Subjects("Biology", false);
    public static final Subjects chemistry = new Subjects("Chemistry", false);
    public static final Subjects commerce = new Subjects("Commerce", false);
    public static final Subjects economics = new Subjects("Economics", false);
    public static final Subjects region = new Subjects("Religious Studies", false);
    public static final Subjects geology = new Subjects("Geology", false);
    public static final Subjects government = new Subjects("Government", false);
    public static final Subjects history = new Subjects("History", false);
    public static final Subjects literature = new Subjects("Literature", false);
    public static final Subjects physics = new Subjects("Physics", false);






    public HomeItemModel[] Models = {
            Post_Utme, Past_Quest, Courses, About
    };


    public static final HomeItemModel Post_Utme = new HomeItemModel(Uri.parse("android.resource://com.honmichael.esutputme/" + R.drawable.esutlogo).toString(),
            "Post Utme", "over 500 past post utme questions"
    );

    public static final HomeItemModel Past_Quest = new HomeItemModel(Uri.parse("android.resource://com.honmichael.esutputme/" + R.drawable.esutlogo).toString(),
            "Past Questions", "Year One past Questions"
    );

    public static final HomeItemModel Courses = new HomeItemModel(Uri.parse("android.resource://com.honmichael.esutputme/" + R.drawable.esutlogo).toString(),
            "Departments", "List of all courses by the Institution"
    );

    public static final HomeItemModel About = new HomeItemModel(Uri.parse("android.resource://com.honmichael.esutputme/" + R.drawable.esutlogo).toString(),
            "About", "About Esut and the App"
    );


    public String[] departments = {"Accountancy", "Agricultural Economics & Extension",
            "Agronomy & Ecological Management", "Agricultural & Bioresource Engineering",
            "Anatomy", "Animal Science & Fisheries Management",
            "Applied Biochemistry", "Applied Biology & Biotechnology",
            "Applied Microbiology and Brewing", "Architecture", "Banking & Finance",
            "Building Technology", "Business Administration",
            "Business Law", "Chemical Engineering", "Civil Engineering",
            "Computer Engineering", "Computer & Information Science", "Coorperative & Rural Development",
            "Electrical & Electronics Engineering", "Educational Foundation", "Estate Management",
            "Economics", "Food Science & Technology", "Geography & Meteorology", "Geology & Mining",
            "Health & Physical Education", "Industrial Chemistry", "Industrial Mathematics & Statistics",
            "Industrial Physics", "Insurance & Risk Management", "International Law and Jurisprudence",
            "Materials & Metallurgical Engineering",
            "Mechanical & Production Engineering", "Marketing", "Mass Communication", "Medical Laboratory Technology",
            "Medical & Surgery", "Public Administration", "Pharmacy", "Political Science", "Private and Property Law",
            "Psychology", "Public Law", "Quantity Surveying", "Science & Computer Education",
            "Sociology & Anthropology", "Surveying & Geoinformatics", "Technology & Vocation Education",
            "Urban & Regional Planning"};
}
