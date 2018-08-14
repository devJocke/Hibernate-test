import Dal.AccessUnicorn;
import Dao.Care;
import Dao.Unicorn;
import org.junit.jupiter.api.Test;

class TestUnicorn {

    @Test
    void CreateUnicorn() {
        Unicorn unicorn = getNewUnicorn();
        assert unicorn.getFirstName().equals("Hej");
    }

    @Test
    void CheckIfUnicornCareIsNull() {
        Unicorn unicorn = getNewUnicorn();
        assert unicorn.getCare() != null;
    }

    @Test
    void CheckIfUnicornNeedsAreNull() {
        Unicorn unicorn = getNewUnicorn();
        for (Care.CareInformation need : unicorn.getCare().getNeeds()) {
            assert need != null;
        }
    }

    @Test
    void CheckIfUnicornHasBeenPreloaded() {
        Unicorn unicorn = AccessUnicorn.preLoadUnicorns();
        assert unicorn != null;
    }

    @Test
    void CheckThatPreloadedUnicornFirstnameIsNotEmptyOrNull() {
        Unicorn unicorn = AccessUnicorn.preLoadUnicorns();
        assert !unicorn.getFirstName().isEmpty() && unicorn.getFirstName() != null;
    }

    private Unicorn getNewUnicorn() {
        return new Unicorn.UnicornBuilder("Hej", "hej", "ej").build();
    }
}