package com.njzhikejia.echohealth.healthlife.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.njzhikejia.echohealth.healthlife.entity.rule.Body_temperature;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BODY_TEMPERATURE".
*/
public class Body_temperatureDao extends AbstractDao<Body_temperature, Void> {

    public static final String TABLENAME = "BODY_TEMPERATURE";

    /**
     * Properties of entity Body_temperature.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Normal_min = new Property(0, double.class, "normal_min", false, "NORMAL_MIN");
        public final static Property Normal_max = new Property(1, double.class, "normal_max", false, "NORMAL_MAX");
        public final static Property Low_min = new Property(2, double.class, "low_min", false, "LOW_MIN");
        public final static Property Low_max = new Property(3, double.class, "low_max", false, "LOW_MAX");
        public final static Property Medium_min = new Property(4, double.class, "medium_min", false, "MEDIUM_MIN");
        public final static Property Medium_max = new Property(5, double.class, "medium_max", false, "MEDIUM_MAX");
        public final static Property High_min = new Property(6, double.class, "high_min", false, "HIGH_MIN");
        public final static Property High_max = new Property(7, double.class, "high_max", false, "HIGH_MAX");
        public final static Property Super_high_min = new Property(8, double.class, "super_high_min", false, "SUPER_HIGH_MIN");
    }


    public Body_temperatureDao(DaoConfig config) {
        super(config);
    }
    
    public Body_temperatureDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BODY_TEMPERATURE\" (" + //
                "\"NORMAL_MIN\" REAL NOT NULL ," + // 0: normal_min
                "\"NORMAL_MAX\" REAL NOT NULL ," + // 1: normal_max
                "\"LOW_MIN\" REAL NOT NULL ," + // 2: low_min
                "\"LOW_MAX\" REAL NOT NULL ," + // 3: low_max
                "\"MEDIUM_MIN\" REAL NOT NULL ," + // 4: medium_min
                "\"MEDIUM_MAX\" REAL NOT NULL ," + // 5: medium_max
                "\"HIGH_MIN\" REAL NOT NULL ," + // 6: high_min
                "\"HIGH_MAX\" REAL NOT NULL ," + // 7: high_max
                "\"SUPER_HIGH_MIN\" REAL NOT NULL );"); // 8: super_high_min
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BODY_TEMPERATURE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Body_temperature entity) {
        stmt.clearBindings();
        stmt.bindDouble(1, entity.getNormal_min());
        stmt.bindDouble(2, entity.getNormal_max());
        stmt.bindDouble(3, entity.getLow_min());
        stmt.bindDouble(4, entity.getLow_max());
        stmt.bindDouble(5, entity.getMedium_min());
        stmt.bindDouble(6, entity.getMedium_max());
        stmt.bindDouble(7, entity.getHigh_min());
        stmt.bindDouble(8, entity.getHigh_max());
        stmt.bindDouble(9, entity.getSuper_high_min());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Body_temperature entity) {
        stmt.clearBindings();
        stmt.bindDouble(1, entity.getNormal_min());
        stmt.bindDouble(2, entity.getNormal_max());
        stmt.bindDouble(3, entity.getLow_min());
        stmt.bindDouble(4, entity.getLow_max());
        stmt.bindDouble(5, entity.getMedium_min());
        stmt.bindDouble(6, entity.getMedium_max());
        stmt.bindDouble(7, entity.getHigh_min());
        stmt.bindDouble(8, entity.getHigh_max());
        stmt.bindDouble(9, entity.getSuper_high_min());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Body_temperature readEntity(Cursor cursor, int offset) {
        Body_temperature entity = new Body_temperature( //
            cursor.getDouble(offset + 0), // normal_min
            cursor.getDouble(offset + 1), // normal_max
            cursor.getDouble(offset + 2), // low_min
            cursor.getDouble(offset + 3), // low_max
            cursor.getDouble(offset + 4), // medium_min
            cursor.getDouble(offset + 5), // medium_max
            cursor.getDouble(offset + 6), // high_min
            cursor.getDouble(offset + 7), // high_max
            cursor.getDouble(offset + 8) // super_high_min
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Body_temperature entity, int offset) {
        entity.setNormal_min(cursor.getDouble(offset + 0));
        entity.setNormal_max(cursor.getDouble(offset + 1));
        entity.setLow_min(cursor.getDouble(offset + 2));
        entity.setLow_max(cursor.getDouble(offset + 3));
        entity.setMedium_min(cursor.getDouble(offset + 4));
        entity.setMedium_max(cursor.getDouble(offset + 5));
        entity.setHigh_min(cursor.getDouble(offset + 6));
        entity.setHigh_max(cursor.getDouble(offset + 7));
        entity.setSuper_high_min(cursor.getDouble(offset + 8));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Body_temperature entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Body_temperature entity) {
        return null;
    }

    @Override
    public boolean hasKey(Body_temperature entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}