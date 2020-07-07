package com.kj.export;

public class CourseExport {
        /**课程名称*/
        private String coursename;
        /**所属院系*/
        private String department;
        /**上课时间*/
        private String time;
        /**老师姓名*/
        private String t_name;
        /**科目*/
        private String category;
        /**学分*/
        private int credit;
        public String getCoursename() {
            return coursename;
        }

        public void setCoursename(String coursename) {
            this.coursename = coursename;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }



        public String getT_name() {
            return t_name;
        }

        public void setT_name(String t_name) {
            this.t_name = t_name;
        }


        @Override
        public String toString() {
            return "Course{" +
                    ", coursename='" + coursename + '\'' +
                    ", department='" + department + '\'' +
                    ", time='" + time + '\'' +
                    ", t_name='" + t_name + '\'' +
                    ", category='" + category + '\'' +
                    ", credit=" + credit +
                    '}';
        }

}
