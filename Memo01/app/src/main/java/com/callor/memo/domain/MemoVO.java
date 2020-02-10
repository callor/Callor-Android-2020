package com.callor.memo.domain;

public class MemoVO {


        private String strDate = "";
        private String strMemo = "";

        public MemoVO() {

        }

        public MemoVO(String strDate, String strMemo) {
            this.strDate = strDate;
            this.strMemo = strMemo;
        }

        public String getStrDate() {
            return strDate;
        }

        public void setStrDate(String strDate) {
            this.strDate = strDate;
        }

        public String getStrMemo() {
            return strMemo;
        }

        public void setStrMemo(String strMemo) {
            this.strMemo = strMemo;
        }

}
