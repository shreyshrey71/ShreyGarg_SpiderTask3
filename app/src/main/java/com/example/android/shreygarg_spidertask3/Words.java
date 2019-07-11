package com.example.android.shreygarg_spidertask3;

import java.util.ArrayList;
import java.util.List;

public class Words {
    List<Results> results;

    public List<Results> getResults()
    {
        return results;
    }
    public Words()
    {
        results = new ArrayList<Results>();
        results.add(new Results());
    }

    class Results
    {
        List<Lexical> lexicalEntries;

        public List<Lexical> getLexicalEntries() {
            return lexicalEntries;
        }
        public Results()
        {
            lexicalEntries = new ArrayList<Lexical>();
            lexicalEntries.add(new Lexical());
        }
        class Lexical
        {
            List<Entries> entries;

            public List<Entries> getEntries() {
                return entries;
            }
            public Lexical()
            {
                entries = new ArrayList<Entries>();
                entries.add(new Entries());
            }
            class Entries
            {
                List<String> etymologies;
                public List<String> getEtymologies() {
                    return etymologies;
                }

                public Entries()
                {
                    etymologies=new ArrayList<String>();
                    etymologies.add("Error");
                }
            }
        }
    }
}
