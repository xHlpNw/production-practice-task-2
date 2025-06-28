public enum CarBodyType {
    SEDAN ("Седан"),
    HATCHBACK ("Хэтчбек"),
    ESTATE ("Универсал"),
    COUPE ("Купе"),
    SUV ("Внедорожник"),
    PICKUP ("Пикап"),
    CONVERTIBLE ("Кабриолет");

    private final String title;

    CarBodyType(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }

}
