public enum MyMonths {

    JAN("Jan"), FEB("Feb"), MAR("Mar"), APR("Apr"), MAY("May"), JUN("Jun"), JUL("Jul"), AUG("Aug"), SEP("Sep"), OCT("Oct"), NOV("Nov"), DEC("Dec");

    private final String value;

    MyMonths(String name){
        this.value = name;
    }

    public String getMonth(){
        return value;
    }
}
