package com.example.win8.easywallet;

/**
 * Created by Win8 on 10/12/2560.
 */

public class BudgetItem {
        public final int id;
        public final String title;
        public final String type;
        public final String money;
        public final String picture;

        public BudgetItem(int id,String type, String title, String money, String picture) {
            this.id = id;
            this.type = type;
            this.title = title;
            this.money = money;
            this.picture = picture;
        }

        @Override
        public String toString() {
            return title;
        }
    }


