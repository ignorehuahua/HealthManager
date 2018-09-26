package com.njzhikejia.echohealth.healthlife.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.njzhikejia.echohealth.healthlife.entity.measure.SpecificData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SPECIFIC_DATA".
*/
public class SpecificDataDao extends AbstractDao<SpecificData, Void> {

    public static final String TABLENAME = "SPECIFIC_DATA";

    /**
     * Properties of entity SpecificData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, int.class, "id", false, "ID");
        public final static Property Uid = new Property(1, int.class, "uid", false, "UID");
        public final static Property Device_id = new Property(2, int.class, "device_id", false, "DEVICE_ID");
        public final static Property Src_type = new Property(3, int.class, "src_type", false, "SRC_TYPE");
        public final static Property Context = new Property(4, int.class, "context", false, "CONTEXT");
        public final static Property Type = new Property(5, int.class, "type", false, "TYPE");
        public final static Property Measure_time = new Property(6, String.class, "measure_time", false, "MEASURE_TIME");
        public final static Property Create_time = new Property(7, String.class, "create_time", false, "CREATE_TIME");
        public final static Property Operator = new Property(8, int.class, "operator", false, "OPERATOR");
        public final static Property Value1 = new Property(9, double.class, "value1", false, "VALUE1");
        public final static Property Value2 = new Property(10, double.class, "value2", false, "VALUE2");
        public final static Property Value3 = new Property(11, double.class, "value3", false, "VALUE3");
        public final static Property Status = new Property(12, int.class, "status", false, "STATUS");
        public final static Property Session_id = new Property(13, int.class, "session_id", false, "SESSION_ID");
        public final static Property Remark = new Property(14, String.class, "remark", false, "REMARK");
        public final static Property Card_uuid = new Property(15, String.class, "card_uuid", false, "CARD_UUID");
        public final static Property Blood_pressure_type = new Property(16, int.class, "blood_pressure_type", false, "BLOOD_PRESSURE_TYPE");
    }


    public SpecificDataDao(DaoConfig config) {
        super(config);
    }
    
    public SpecificDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SPECIFIC_DATA\" (" + //
                "\"ID\" INTEGER NOT NULL ," + // 0: id
                "\"UID\" INTEGER NOT NULL ," + // 1: uid
                "\"DEVICE_ID\" INTEGER NOT NULL ," + // 2: device_id
                "\"SRC_TYPE\" INTEGER NOT NULL ," + // 3: src_type
                "\"CONTEXT\" INTEGER NOT NULL ," + // 4: context
                "\"TYPE\" INTEGER NOT NULL ," + // 5: type
                "\"MEASURE_TIME\" TEXT," + // 6: measure_time
                "\"CREATE_TIME\" TEXT," + // 7: create_time
                "\"OPERATOR\" INTEGER NOT NULL ," + // 8: operator
                "\"VALUE1\" REAL NOT NULL ," + // 9: value1
                "\"VALUE2\" REAL NOT NULL ," + // 10: value2
                "\"VALUE3\" REAL NOT NULL ," + // 11: value3
                "\"STATUS\" INTEGER NOT NULL ," + // 12: status
                "\"SESSION_ID\" INTEGER NOT NULL ," + // 13: session_id
                "\"REMARK\" TEXT," + // 14: remark
                "\"CARD_UUID\" TEXT," + // 15: card_uuid
                "\"BLOOD_PRESSURE_TYPE\" INTEGER NOT NULL );"); // 16: blood_pressure_type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SPECIFIC_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SpecificData entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getUid());
        stmt.bindLong(3, entity.getDevice_id());
        stmt.bindLong(4, entity.getSrc_type());
        stmt.bindLong(5, entity.getContext());
        stmt.bindLong(6, entity.getType());
 
        String measure_time = entity.getMeasure_time();
        if (measure_time != null) {
            stmt.bindString(7, measure_time);
        }
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(8, create_time);
        }
        stmt.bindLong(9, entity.getOperator());
        stmt.bindDouble(10, entity.getValue1());
        stmt.bindDouble(11, entity.getValue2());
        stmt.bindDouble(12, entity.getValue3());
        stmt.bindLong(13, entity.getStatus());
        stmt.bindLong(14, entity.getSession_id());
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(15, remark);
        }
 
        String card_uuid = entity.getCard_uuid();
        if (card_uuid != null) {
            stmt.bindString(16, card_uuid);
        }
        stmt.bindLong(17, entity.getBlood_pressure_type());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SpecificData entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getUid());
        stmt.bindLong(3, entity.getDevice_id());
        stmt.bindLong(4, entity.getSrc_type());
        stmt.bindLong(5, entity.getContext());
        stmt.bindLong(6, entity.getType());
 
        String measure_time = entity.getMeasure_time();
        if (measure_time != null) {
            stmt.bindString(7, measure_time);
        }
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(8, create_time);
        }
        stmt.bindLong(9, entity.getOperator());
        stmt.bindDouble(10, entity.getValue1());
        stmt.bindDouble(11, entity.getValue2());
        stmt.bindDouble(12, entity.getValue3());
        stmt.bindLong(13, entity.getStatus());
        stmt.bindLong(14, entity.getSession_id());
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(15, remark);
        }
 
        String card_uuid = entity.getCard_uuid();
        if (card_uuid != null) {
            stmt.bindString(16, card_uuid);
        }
        stmt.bindLong(17, entity.getBlood_pressure_type());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public SpecificData readEntity(Cursor cursor, int offset) {
        SpecificData entity = new SpecificData( //
            cursor.getInt(offset + 0), // id
            cursor.getInt(offset + 1), // uid
            cursor.getInt(offset + 2), // device_id
            cursor.getInt(offset + 3), // src_type
            cursor.getInt(offset + 4), // context
            cursor.getInt(offset + 5), // type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // measure_time
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // create_time
            cursor.getInt(offset + 8), // operator
            cursor.getDouble(offset + 9), // value1
            cursor.getDouble(offset + 10), // value2
            cursor.getDouble(offset + 11), // value3
            cursor.getInt(offset + 12), // status
            cursor.getInt(offset + 13), // session_id
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // remark
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // card_uuid
            cursor.getInt(offset + 16) // blood_pressure_type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SpecificData entity, int offset) {
        entity.setId(cursor.getInt(offset + 0));
        entity.setUid(cursor.getInt(offset + 1));
        entity.setDevice_id(cursor.getInt(offset + 2));
        entity.setSrc_type(cursor.getInt(offset + 3));
        entity.setContext(cursor.getInt(offset + 4));
        entity.setType(cursor.getInt(offset + 5));
        entity.setMeasure_time(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCreate_time(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setOperator(cursor.getInt(offset + 8));
        entity.setValue1(cursor.getDouble(offset + 9));
        entity.setValue2(cursor.getDouble(offset + 10));
        entity.setValue3(cursor.getDouble(offset + 11));
        entity.setStatus(cursor.getInt(offset + 12));
        entity.setSession_id(cursor.getInt(offset + 13));
        entity.setRemark(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setCard_uuid(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setBlood_pressure_type(cursor.getInt(offset + 16));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(SpecificData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(SpecificData entity) {
        return null;
    }

    @Override
    public boolean hasKey(SpecificData entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
