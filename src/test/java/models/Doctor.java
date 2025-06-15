package models;

public class Doctor {

    public enum Specialty {
        GASTROENTEROLOGIST("Гастроэнтеролог", "gastroenterolog", "Гастроэнтерологи"),
        CARDIOLOGIST("Кардиолог", "kardiolog", "Кардиологи");

        private final String displayName;
        private final String urlPart;
        private final String pageTitle;

        Specialty(String displayName, String urlPart, String pageTitle) {
            this.displayName = displayName;
            this.urlPart = urlPart;
            this.pageTitle = pageTitle;
        }

        public String getDisplayName() { return displayName; }
        public String getUrlPart() { return urlPart; }
        public String getPageTitle() { return pageTitle; }
    }
}