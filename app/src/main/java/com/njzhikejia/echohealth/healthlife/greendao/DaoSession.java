package com.njzhikejia.echohealth.healthlife.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.njzhikejia.echohealth.healthlife.entity.Message;

import com.njzhikejia.echohealth.healthlife.greendao.MessageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig messageDaoConfig;

    private final MessageDao messageDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        messageDaoConfig = daoConfigMap.get(MessageDao.class).clone();
        messageDaoConfig.initIdentityScope(type);

        messageDao = new MessageDao(messageDaoConfig, this);

        registerDao(Message.class, messageDao);
    }
    
    public void clear() {
        messageDaoConfig.clearIdentityScope();
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

}
