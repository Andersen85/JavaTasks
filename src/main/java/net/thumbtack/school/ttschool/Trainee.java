package net.thumbtack.school.ttschool;

public class Trainee {

    private String firstName;
    private String lastName;
    private int rating;

    public Trainee(String firstName, String lastName, int rating) throws TrainingException {
        //Создает Trainee с указанными значениями полей.Для недопустимых значений входных параметров выбрасывает
        //TrainingException с соответствующим TrainingErrorCode
        if(firstName==null||firstName.equals("")){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME);
        }

        if(lastName==null||lastName.equals("")){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_LASTNAME);
        }

        if(rating<1||rating>5){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws TrainingException{
        String surname;
        if(firstName==null||firstName.equals("")){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME);
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws TrainingException {
        if(lastName==null||lastName.equals("")){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_LASTNAME);
        }
        this.lastName = lastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws TrainingException {
        if(rating<1||rating>5){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING);
        }
        this.rating = rating;
    }

    public String getFullName() {
        //Возвращает полное имя учащегося (имя и фамилию, разделенные пробелом)
        return firstName+" "+lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainee trainee = (Trainee) o;

        if (getRating() != trainee.getRating()) return false;
        if (getFirstName() != null ? !getFirstName().equals(trainee.getFirstName()) : trainee.getFirstName() != null)
            return false;
        return getLastName() != null ? getLastName().equals(trainee.getLastName()) : trainee.getLastName() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + getRating();
        return result;
    }
}
