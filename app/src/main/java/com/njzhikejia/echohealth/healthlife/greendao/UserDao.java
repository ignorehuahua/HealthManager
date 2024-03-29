package com.njzhikejia.echohealth.healthlife.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.njzhikejia.echohealth.healthlife.entity.user.Extend;
import com.njzhikejia.echohealth.healthlife.entity.user.User.ExtendConverter;

import com.njzhikejia.echohealth.healthlife.entity.user.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Tenant_id = new Property(1, int.class, "tenant_id", false, "TENANT_ID");
        public final static Property House_id = new Property(2, int.class, "house_id", false, "HOUSE_ID");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Nickname = new Property(4, String.class, "nickname", false, "NICKNAME");
        public final static Property Idcard = new Property(5, String.class, "idcard", false, "IDCARD");
        public final static Property Phone = new Property(6, String.class, "phone", false, "PHONE");
        public final static Property Gender = new Property(7, int.class, "gender", false, "GENDER");
        public final static Property Birthday = new Property(8, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Home_addr = new Property(9, String.class, "home_addr", false, "HOME_ADDR");
        public final static Property Status = new Property(10, int.class, "status", false, "STATUS");
        public final static Property Avatar = new Property(11, String.class, "avatar", false, "AVATAR");
        public final static Property Create_time = new Property(12, String.class, "create_time", false, "CREATE_TIME");
        public final static Property Remark = new Property(13, String.class, "remark", false, "REMARK");
        public final static Property Extend = new Property(14, String.class, "extend", false, "EXTEND");
    }

    private final ExtendConverter extendConverter = new ExtendConverter();

    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TENANT_ID\" INTEGER NOT NULL ," + // 1: tenant_id
                "\"HOUSE_ID\" INTEGER NOT NULL ," + // 2: house_id
                "\"NAME\" TEXT," + // 3: name
                "\"NICKNAME\" TEXT," + // 4: nickname
                "\"IDCARD\" TEXT," + // 5: idcard
                "\"PHONE\" TEXT," + // 6: phone
                "\"GENDER\" INTEGER NOT NULL ," + // 7: gender
                "\"BIRTHDAY\" TEXT," + // 8: birthday
                "\"HOME_ADDR\" TEXT," + // 9: home_addr
                "\"STATUS\" INTEGER NOT NULL ," + // 10: status
                "\"AVATAR\" TEXT," + // 11: avatar
                "\"CREATE_TIME\" TEXT," + // 12: create_time
                "\"REMARK\" TEXT," + // 13: remark
                "\"EXTEND\" TEXT);"); // 14: extend
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getTenant_id());
        stmt.bindLong(3, entity.getHouse_id());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(5, nickname);
        }
 
        String idcard = entity.getIdcard();
        if (idcard != null) {
            stmt.bindString(6, idcard);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(7, phone);
        }
        stmt.bindLong(8, entity.getGender());
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(9, birthday);
        }
 
        String home_addr = entity.getHome_addr();
        if (home_addr != null) {
            stmt.bindString(10, home_addr);
        }
        stmt.bindLong(11, entity.getStatus());
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(12, avatar);
        }
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(13, create_time);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(14, remark);
        }
 
        Extend extend = entity.getExtend();
        if (extend != null) {
            stmt.bindString(15, extendConverter.convertToDatabaseValue(extend));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getTenant_id());
        stmt.bindLong(3, entity.getHouse_id());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(5, nickname);
        }
 
        String idcard = entity.getIdcard();
        if (idcard != null) {
            stmt.bindString(6, idcard);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(7, phone);
        }
        stmt.bindLong(8, entity.getGender());
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(9, birthday);
        }
 
        String home_addr = entity.getHome_addr();
        if (home_addr != null) {
            stmt.bindString(10, home_addr);
        }
        stmt.bindLong(11, entity.getStatus());
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(12, avatar);
        }
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(13, create_time);
        }
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(14, remark);
        }
 
        Extend extend = entity.getExtend();
        if (extend != null) {
            stmt.bindString(15, extendConverter.convertToDatabaseValue(extend));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // tenant_id
            cursor.getInt(offset + 2), // house_id
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // nickname
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // idcard
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // phone
            cursor.getInt(offset + 7), // gender
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // birthday
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // home_addr
            cursor.getInt(offset + 10), // status
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // avatar
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // create_time
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // remark
            cursor.isNull(offset + 14) ? null : extendConverter.convertToEntityProperty(cursor.getString(offset + 14)) // extend
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTenant_id(cursor.getInt(offset + 1));
        entity.setHouse_id(cursor.getInt(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNickname(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIdcard(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPhone(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGender(cursor.getInt(offset + 7));
        entity.setBirthday(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setHome_addr(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setStatus(cursor.getInt(offset + 10));
        entity.setAvatar(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCreate_time(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setRemark(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setExtend(cursor.isNull(offset + 14) ? null : extendConverter.convertToEntityProperty(cursor.getString(offset + 14)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
