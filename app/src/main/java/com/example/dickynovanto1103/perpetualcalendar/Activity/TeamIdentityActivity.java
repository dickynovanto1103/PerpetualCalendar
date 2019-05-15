package com.example.dickynovanto1103.perpetualcalendar.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dickynovanto1103.perpetualcalendar.Language;
import com.example.dickynovanto1103.perpetualcalendar.R;

public class TeamIdentityActivity extends AppCompatActivity {
    TextView teamMembers, title, titleTeamIdentity, subjectAndInstitution;
    Language language = Language.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_identity);
        teamMembers = findViewById(R.id.team_member);
        titleTeamIdentity = findViewById(R.id.title_team_identity);
        title = findViewById(R.id.disusun_oleh);
        subjectAndInstitution = findViewById(R.id.subjectAndInstitution);
        setTeamMembers();
        setTitle();
        setTitleTeamIdentity();
        setSubjectAndInstitution();
    }

    private void setTitle() {
        String judul;
        if(language.getBahasa() == 0) {
            judul = "Created by";
        }else{
            judul = "Disusun oleh";
        }
        title.setText(judul);
    }

    private void setTeamMembers() {
        String[] names = new String[] { "Yuniar Sintha Ully", "Jasmine Jacintha", "Yosia Jaya Kosasih", "Dicky Novanto" };
        String[] nim = new String[] { "10117051", "10117059", "10515035", "13515134" };
        String teamIdentity = "";
        for(int i=0;i<names.length;i++) {
            teamIdentity += names[i] + " (" + nim[i] + ")\n";
        }
        teamMembers.setText(teamIdentity);
    }

    private void setTitleTeamIdentity() {
        String content;
        if(language.getBahasa() == 0) {
            content = "Perpetual Calendar Team";
        }else {
            content = "Kelompok Perpetual Calendar";
        }
        titleTeamIdentity.setText(content);
    }

    private void setSubjectAndInstitution() {
        String content = "MA 2252 Pengantar Teori Bilangan ";
        if(language.getBahasa() == 0){
            content += "2nd Semester";
        }else{
            content += "Semester 2";
        }
        content += "\n";
        content += "Institut Teknologi Bandung";
        subjectAndInstitution.setText(content);
    }
}
