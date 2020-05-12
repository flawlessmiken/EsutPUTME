package com.honmichael.esutputme;

import com.honmichael.esutputme.model.HomeItemModel;
import com.honmichael.esutputme.model.Subjects;

import java.util.ArrayList;

public interface IMainActivity {

    void inflateChooseSubjectsFragment(HomeItemModel model);
    void onSelectSubjectsAction(int position);

    void inflateExamBoardActivity(ArrayList<Subjects> selectedsubjects);
}
