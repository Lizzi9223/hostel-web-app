package by.epam.tc.web.dao.database.metadata;

public final class Metadata {

    public final static String USERS_TABLE = "users";
    public final static String USER_ROLES_TABLE = "user_roles";
    public final static String ADMINS_TABLE = "admins";
    public final static String ALL_CLIENTS_TABLE = "all_clients";
    public final static String BLACK_LIST_TABLE = "blacklist";
    public final static String REGULAR_CUSTOMERS_TABLE = "regular_customers";
    public final static String ALL_STAYS_TABLE = "all_stays";
    public final static String ROOMS_TABLE = "rooms";
    public final static String IMAGES_TABLE = "images";
    public final static String BOOKING_TABLE = "booking";

    public final class UsersTableColumn{
        public final static String USER_ID = "user_id";
        public final static String LOGIN = "login";
        public final static String PASSWORD = "password";
        public final static String ROLE_ID = "role_id";
    }

    public final class UserRolesTableColumn{
        public final static String ROLE_ID = "role_id";
        public final static String ROLE_NAME = "role_name";
    }

    public final class AdminsTableColumn{
        public final static String USER_ID = "user_id";
        public final static String NAME = "name";
        public final static String PHOTO = "photo";
    }

    public final class AllClientsTableColumn{
        public final static String CLIENT_ID = "client_id";
        public final static String USER_ID = "user_id";
        public final static String FIRST_NAME = "first_name";
        public final static String LAST_NAME = "last_name";
        public final static String PASSPORT_ID = "passport_id";
        public final static String DATE_OF_BIRTH = "date_of_birth";
        public final static String COUNTY = "country";
        public final static String PHONE_NUMBER = "phone_number";
        public final static String EMAIL = "email";
    }

    public final class BlackListTableColumn{
        public final static String CLIENT_ID = "client_id";
        public final static String REASON = "reason";
        public final static String SINCE_DATE = "since_date";
    }

    public final class RegularCustomersTableColumn{
        public final static String CLIENT_ID = "client_id";
        public final static String SINCE_DATE = "since_date";
        public final static String DISCOUNT = "discount";
        public final static String NOTES = "notes";
    }

    public final class AllStaysTableColumn{
        public final static String STAY_ID = "stay_id";
        public final static String CLIENT_ID = "client_id";
        public final static String ROOM_NUMBER = "room_number";
        public final static String FROM_DATE = "from_date";
        public final static String TO_DATE = "to_date";
        public final static String NOTES = "notes";
    }

    public final class RoomsTableColumn{
        public final static String ROOM_NUMBER = "room_number";
        public final static String COST = "cost";
        public final static String CAPACITY = "capacity";
        public final static String GENDER = "gender";
        public final static String BATHROOM = "bathroom";
        public final static String NOTES = "notes";
    }

    public final class ImagesTableColumn{
        public final static String IMG_ID = "img_id";
        public final static String IMG_PATH = "img_path";
        public final static String ROOM_NUMBER = "room_number";
    }

    public final class BookingTableColumn{
        public final static String BOOKING_ID = "booking_id";
        public final static String USER_ID = "user_id";
        public final static String ROOM_NUMBER = "room_number";
        public final static String FROM_DATE = "from_date";
        public final static String TO_DATE = "to_date";
        public final static String GUESTS_COUNT = "guests_count";
        public final static String APPROVED = "approved";
        public final static String APPROVE_DATE = "approve_date";
        public final static String PAID = "paid";
    }

    private Metadata(){}
}
