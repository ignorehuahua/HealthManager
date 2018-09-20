package com.njzhikejia.echohealth.healthlife.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.njzhikejia.echohealth.healthlife.entity.Concerneds;
import com.njzhikejia.echohealth.healthlife.entity.Concerns;
import com.njzhikejia.echohealth.healthlife.entity.Extend;
import com.njzhikejia.echohealth.healthlife.entity.Message;
import com.njzhikejia.echohealth.healthlife.entity.Reports;
import com.njzhikejia.echohealth.healthlife.entity.SpecificData;
import com.njzhikejia.echohealth.healthlife.entity.User;
import com.njzhikejia.echohealth.healthlife.entity.warn.Measure;
import com.njzhikejia.echohealth.healthlife.entity.warn.Notices;
import com.njzhikejia.echohealth.healthlife.entity.warn.RegionAdamin;
import com.njzhikejia.echohealth.healthlife.entity.warn.SrcData;

import com.njzhikejia.echohealth.healthlife.greendao.ConcernedsDao;
import com.njzhikejia.echohealth.healthlife.greendao.ConcernsDao;
import com.njzhikejia.echohealth.healthlife.greendao.ExtendDao;
import com.njzhikejia.echohealth.healthlife.greendao.MessageDao;
import com.njzhikejia.echohealth.healthlife.greendao.ReportsDao;
import com.njzhikejia.echohealth.healthlife.greendao.SpecificDataDao;
import com.njzhikejia.echohealth.healthlife.greendao.UserDao;
import com.njzhikejia.echohealth.healthlife.greendao.MeasureDao;
import com.njzhikejia.echohealth.healthlife.greendao.NoticesDao;
import com.njzhikejia.echohealth.healthlife.greendao.RegionAdaminDao;
import com.njzhikejia.echohealth.healthlife.greendao.SrcDataDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig concernedsDaoConfig;
    private final DaoConfig concernsDaoConfig;
    private final DaoConfig extendDaoConfig;
    private final DaoConfig messageDaoConfig;
    private final DaoConfig reportsDaoConfig;
    private final DaoConfig specificDataDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig measureDaoConfig;
    private final DaoConfig noticesDaoConfig;
    private final DaoConfig regionAdaminDaoConfig;
    private final DaoConfig srcDataDaoConfig;

    private final ConcernedsDao concernedsDao;
    private final ConcernsDao concernsDao;
    private final ExtendDao extendDao;
    private final MessageDao messageDao;
    private final ReportsDao reportsDao;
    private final SpecificDataDao specificDataDao;
    private final UserDao userDao;
    private final MeasureDao measureDao;
    private final NoticesDao noticesDao;
    private final RegionAdaminDao regionAdaminDao;
    private final SrcDataDao srcDataDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        concernedsDaoConfig = daoConfigMap.get(ConcernedsDao.class).clone();
        concernedsDaoConfig.initIdentityScope(type);

        concernsDaoConfig = daoConfigMap.get(ConcernsDao.class).clone();
        concernsDaoConfig.initIdentityScope(type);

        extendDaoConfig = daoConfigMap.get(ExtendDao.class).clone();
        extendDaoConfig.initIdentityScope(type);

        messageDaoConfig = daoConfigMap.get(MessageDao.class).clone();
        messageDaoConfig.initIdentityScope(type);

        reportsDaoConfig = daoConfigMap.get(ReportsDao.class).clone();
        reportsDaoConfig.initIdentityScope(type);

        specificDataDaoConfig = daoConfigMap.get(SpecificDataDao.class).clone();
        specificDataDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        measureDaoConfig = daoConfigMap.get(MeasureDao.class).clone();
        measureDaoConfig.initIdentityScope(type);

        noticesDaoConfig = daoConfigMap.get(NoticesDao.class).clone();
        noticesDaoConfig.initIdentityScope(type);

        regionAdaminDaoConfig = daoConfigMap.get(RegionAdaminDao.class).clone();
        regionAdaminDaoConfig.initIdentityScope(type);

        srcDataDaoConfig = daoConfigMap.get(SrcDataDao.class).clone();
        srcDataDaoConfig.initIdentityScope(type);

        concernedsDao = new ConcernedsDao(concernedsDaoConfig, this);
        concernsDao = new ConcernsDao(concernsDaoConfig, this);
        extendDao = new ExtendDao(extendDaoConfig, this);
        messageDao = new MessageDao(messageDaoConfig, this);
        reportsDao = new ReportsDao(reportsDaoConfig, this);
        specificDataDao = new SpecificDataDao(specificDataDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        measureDao = new MeasureDao(measureDaoConfig, this);
        noticesDao = new NoticesDao(noticesDaoConfig, this);
        regionAdaminDao = new RegionAdaminDao(regionAdaminDaoConfig, this);
        srcDataDao = new SrcDataDao(srcDataDaoConfig, this);

        registerDao(Concerneds.class, concernedsDao);
        registerDao(Concerns.class, concernsDao);
        registerDao(Extend.class, extendDao);
        registerDao(Message.class, messageDao);
        registerDao(Reports.class, reportsDao);
        registerDao(SpecificData.class, specificDataDao);
        registerDao(User.class, userDao);
        registerDao(Measure.class, measureDao);
        registerDao(Notices.class, noticesDao);
        registerDao(RegionAdamin.class, regionAdaminDao);
        registerDao(SrcData.class, srcDataDao);
    }
    
    public void clear() {
        concernedsDaoConfig.clearIdentityScope();
        concernsDaoConfig.clearIdentityScope();
        extendDaoConfig.clearIdentityScope();
        messageDaoConfig.clearIdentityScope();
        reportsDaoConfig.clearIdentityScope();
        specificDataDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        measureDaoConfig.clearIdentityScope();
        noticesDaoConfig.clearIdentityScope();
        regionAdaminDaoConfig.clearIdentityScope();
        srcDataDaoConfig.clearIdentityScope();
    }

    public ConcernedsDao getConcernedsDao() {
        return concernedsDao;
    }

    public ConcernsDao getConcernsDao() {
        return concernsDao;
    }

    public ExtendDao getExtendDao() {
        return extendDao;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public ReportsDao getReportsDao() {
        return reportsDao;
    }

    public SpecificDataDao getSpecificDataDao() {
        return specificDataDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public MeasureDao getMeasureDao() {
        return measureDao;
    }

    public NoticesDao getNoticesDao() {
        return noticesDao;
    }

    public RegionAdaminDao getRegionAdaminDao() {
        return regionAdaminDao;
    }

    public SrcDataDao getSrcDataDao() {
        return srcDataDao;
    }

}
