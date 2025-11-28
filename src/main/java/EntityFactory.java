public class EntityFactory {

    public static Entity createEntity(EntityType type, int x, int y) {
        switch (type) {
            case CITIZEN:
                return new Citizen(x, y);
            case HOUSE:
                return new House(x, y);
            case SAVEZONE:
                return new SaveZone(x, y);
            default:
                throw new IllegalArgumentException("Non existent entity type");
        }
    }
    public static Entity createEntity(EntityType type) {
        switch (type) {
            case CITIZEN:
                return new Citizen();
            case HOUSE:
                return new House();
            case SAVEZONE:
                return new SaveZone();
            default:
                throw new IllegalArgumentException("Non existent entity type");
        }
    }

    public static Entity createEntity(EntityType type, int x, int y, int houseNumber) {
        switch (type) {
            case CITIZEN:
                return new Citizen(x, y, houseNumber);
                default:
                    throw new IllegalArgumentException("This entity does not have houseNumber");
        }
    }
}