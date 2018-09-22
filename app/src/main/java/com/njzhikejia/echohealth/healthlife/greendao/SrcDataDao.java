package com.njzhikejia.echohealth.healthlife.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.njzhikejia.echohealth.healthlife.entity.warn.Measure;
import com.njzhikejia.echohealth.healthlife.entity.warn.RegionAdamin;
import com.njzhikejia.echohealth.healthlife.entity.warn.SrcData.MeasureConverter;
import com.njzhikejia.echohealth.healthlife.entity.warn.SrcData.RegionAdaminConverter;

import com.njzhikejia.echohealth.healthlife.entity.warn.SrcData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SRC_DATA".
*/
public class SrcDataDao extends AbstractDao<SrcData, Void> {

    public static final String TABLENAME = "SRC_DATA";

    /**
     * Properties of entity SrcData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Measure = new Property(0, String.class, "measure", false, "MEASURE");
        public final static Property Region_adamin = new Property(1, String.class, "region_adamin", false, "REGION_ADAMIN");
    }

    private final MeasureConverter measureConverter = new MeasureConverter();
    private final RegionAdaminConverter region_adaminConverter = new RegionAdaminConverter();

    public SrcDataDao(DaoConfig config) {
        super(config);
    }
    
    public SrcDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SRC_DATA\" (" + //
                "\"MEASURE\" TEXT," + // 0: measure
                "\"REGION_ADAMIN\" TEXT);"); // 1: region_adamin
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SRC_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SrcData entity) {
        stmt.clearBindings();
 
        Measure measure = entity.getMeasure();
        if (measure != null) {
            stmt.bindString(1, measureConverter.convertToDatabaseValue(measure));
        }
 
        RegionAdamin region_adamin = entity.getRegion_adamin();
        if (region_adamin != null) {
            stmt.bindString(2, region_adaminConverter.convertToDatabaseValue(region_adamin));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SrcData entity) {
        stmt.clearBindings();
 
        Measure measure = entity.getMeasure();
        if (measure != null) {
            stmt.bindString(1, measureConverter.convertToDatabaseValue(measure));
        }
 
        RegionAdamin region_adamin = entity.getRegion_adamin();
        if (region_adamin != null) {
            stmt.bindString(2, region_adaminConverter.convertToDatabaseValue(region_adamin));
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public SrcData readEntity(Cursor cursor, int offset) {
        SrcData entity = new SrcData( //
            cursor.isNull(offset + 0) ? null : measureConverter.convertToEntityProperty(cursor.getString(offset + 0)), // measure
            cursor.isNull(offset + 1) ? null : region_adaminConverter.convertToEntityProperty(cursor.getString(offset + 1)) // region_adamin
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SrcData entity, int offset) {
        entity.setMeasure(cursor.isNull(offset + 0) ? null : measureConverter.convertToEntityProperty(cursor.getString(offset + 0)));
        entity.setRegion_adamin(cursor.isNull(offset + 1) ? null : region_adaminConverter.convertToEntityProperty(cursor.getString(offset + 1)));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(SrcData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(SrcData entity) {
        return null;
    }

    @Override
    public boolean hasKey(SrcData entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}