package com.njzhikejia.echohealth.healthlife.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.njzhikejia.echohealth.healthlife.entity.rule.Blood_sugar;
import com.njzhikejia.echohealth.healthlife.entity.rule.Body_temperature;
import com.njzhikejia.echohealth.healthlife.entity.rule.Bp;
import com.njzhikejia.echohealth.healthlife.entity.rule.Pulse;
import com.njzhikejia.echohealth.healthlife.entity.rule.RuleResult.Blood_sugarConverter;
import com.njzhikejia.echohealth.healthlife.entity.rule.RuleResult.Body_temperatureConverter;
import com.njzhikejia.echohealth.healthlife.entity.rule.RuleResult.BpConverter;
import com.njzhikejia.echohealth.healthlife.entity.rule.RuleResult.PulseConverter;
import com.njzhikejia.echohealth.healthlife.entity.rule.RuleResult.Spo2Converter;
import com.njzhikejia.echohealth.healthlife.entity.rule.Spo2;

import com.njzhikejia.echohealth.healthlife.entity.rule.RuleResult;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RULE_RESULT".
*/
public class RuleResultDao extends AbstractDao<RuleResult, Void> {

    public static final String TABLENAME = "RULE_RESULT";

    /**
     * Properties of entity RuleResult.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Uid = new Property(0, int.class, "uid", false, "UID");
        public final static Property Pulse = new Property(1, String.class, "pulse", false, "PULSE");
        public final static Property Bp = new Property(2, String.class, "bp", false, "BP");
        public final static Property Spo2 = new Property(3, String.class, "spo2", false, "SPO2");
        public final static Property Blood_sugar = new Property(4, String.class, "blood_sugar", false, "BLOOD_SUGAR");
        public final static Property Body_temperature = new Property(5, String.class, "body_temperature", false, "BODY_TEMPERATURE");
    }

    private final PulseConverter pulseConverter = new PulseConverter();
    private final BpConverter bpConverter = new BpConverter();
    private final Spo2Converter spo2Converter = new Spo2Converter();
    private final Blood_sugarConverter blood_sugarConverter = new Blood_sugarConverter();
    private final Body_temperatureConverter body_temperatureConverter = new Body_temperatureConverter();

    public RuleResultDao(DaoConfig config) {
        super(config);
    }
    
    public RuleResultDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RULE_RESULT\" (" + //
                "\"UID\" INTEGER NOT NULL ," + // 0: uid
                "\"PULSE\" TEXT," + // 1: pulse
                "\"BP\" TEXT," + // 2: bp
                "\"SPO2\" TEXT," + // 3: spo2
                "\"BLOOD_SUGAR\" TEXT," + // 4: blood_sugar
                "\"BODY_TEMPERATURE\" TEXT);"); // 5: body_temperature
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RULE_RESULT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RuleResult entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUid());
 
        Pulse pulse = entity.getPulse();
        if (pulse != null) {
            stmt.bindString(2, pulseConverter.convertToDatabaseValue(pulse));
        }
 
        Bp bp = entity.getBp();
        if (bp != null) {
            stmt.bindString(3, bpConverter.convertToDatabaseValue(bp));
        }
 
        Spo2 spo2 = entity.getSpo2();
        if (spo2 != null) {
            stmt.bindString(4, spo2Converter.convertToDatabaseValue(spo2));
        }
 
        Blood_sugar blood_sugar = entity.getBlood_sugar();
        if (blood_sugar != null) {
            stmt.bindString(5, blood_sugarConverter.convertToDatabaseValue(blood_sugar));
        }
 
        Body_temperature body_temperature = entity.getBody_temperature();
        if (body_temperature != null) {
            stmt.bindString(6, body_temperatureConverter.convertToDatabaseValue(body_temperature));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RuleResult entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUid());
 
        Pulse pulse = entity.getPulse();
        if (pulse != null) {
            stmt.bindString(2, pulseConverter.convertToDatabaseValue(pulse));
        }
 
        Bp bp = entity.getBp();
        if (bp != null) {
            stmt.bindString(3, bpConverter.convertToDatabaseValue(bp));
        }
 
        Spo2 spo2 = entity.getSpo2();
        if (spo2 != null) {
            stmt.bindString(4, spo2Converter.convertToDatabaseValue(spo2));
        }
 
        Blood_sugar blood_sugar = entity.getBlood_sugar();
        if (blood_sugar != null) {
            stmt.bindString(5, blood_sugarConverter.convertToDatabaseValue(blood_sugar));
        }
 
        Body_temperature body_temperature = entity.getBody_temperature();
        if (body_temperature != null) {
            stmt.bindString(6, body_temperatureConverter.convertToDatabaseValue(body_temperature));
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public RuleResult readEntity(Cursor cursor, int offset) {
        RuleResult entity = new RuleResult( //
            cursor.getInt(offset + 0), // uid
            cursor.isNull(offset + 1) ? null : pulseConverter.convertToEntityProperty(cursor.getString(offset + 1)), // pulse
            cursor.isNull(offset + 2) ? null : bpConverter.convertToEntityProperty(cursor.getString(offset + 2)), // bp
            cursor.isNull(offset + 3) ? null : spo2Converter.convertToEntityProperty(cursor.getString(offset + 3)), // spo2
            cursor.isNull(offset + 4) ? null : blood_sugarConverter.convertToEntityProperty(cursor.getString(offset + 4)), // blood_sugar
            cursor.isNull(offset + 5) ? null : body_temperatureConverter.convertToEntityProperty(cursor.getString(offset + 5)) // body_temperature
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RuleResult entity, int offset) {
        entity.setUid(cursor.getInt(offset + 0));
        entity.setPulse(cursor.isNull(offset + 1) ? null : pulseConverter.convertToEntityProperty(cursor.getString(offset + 1)));
        entity.setBp(cursor.isNull(offset + 2) ? null : bpConverter.convertToEntityProperty(cursor.getString(offset + 2)));
        entity.setSpo2(cursor.isNull(offset + 3) ? null : spo2Converter.convertToEntityProperty(cursor.getString(offset + 3)));
        entity.setBlood_sugar(cursor.isNull(offset + 4) ? null : blood_sugarConverter.convertToEntityProperty(cursor.getString(offset + 4)));
        entity.setBody_temperature(cursor.isNull(offset + 5) ? null : body_temperatureConverter.convertToEntityProperty(cursor.getString(offset + 5)));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(RuleResult entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(RuleResult entity) {
        return null;
    }

    @Override
    public boolean hasKey(RuleResult entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
