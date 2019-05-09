package com.example.dickynovanto1103.perpetualcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TeamIdentityActivity extends AppCompatActivity {
    TextView teamMembers, title, titleTeamIdentity;
    Language language = Language.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_identity);
        teamMembers = findViewById(R.id.team_member);
        titleTeamIdentity = findViewById(R.id.title_team_identity);
        title = findViewById(R.id.disusun_oleh);
        setTeamMembers();
        setTitle();
        setTitleTeamIdentity();
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
            content = "Team Members";
        }else {
            content = "Anggota Tim";
        }
        titleTeamIdentity.setText(content);
    }
}
