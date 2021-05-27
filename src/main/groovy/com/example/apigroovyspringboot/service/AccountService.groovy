package com.example.apigroovyspringboot.service

import com.example.apigroovyspringboot.model.Account
import com.example.apigroovyspringboot.model.Transaction
import com.example.apigroovyspringboot.repository.AccountRepository
import com.example.apigroovyspringboot.util.ThrowUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountService extends BaseService<Account>{

    public static final BigDecimal AVALIABLE_CREDIT_LIMIT_INITIAL = 5000

    @Autowired
    AccountRepository repository

    @Override
    Account save(Account entity) {

        if(!entity.id){

            entity.availableCreditLimit = AVALIABLE_CREDIT_LIMIT_INITIAL
        }

        return super.save(entity)
    }

    void validateLimit(Account account, Transaction transaction) {

        if(transaction.operationType.isWithdraw()){

            ThrowUtil.accountLimitExceptionThrow(account.availableCreditLimit <= 0, "Available credit limit is not enough")

            BigDecimal balance = account.availableCreditLimit.subtract(transaction.amount)

            ThrowUtil.accountLimitExceptionThrow(balance < 0, "Insufficient credit limit")
        }

    }

    void sensitizesLimit(Account account, Transaction transaction) {

        this.validateLimit(account, transaction)

        if(transaction.operationType.isWithdraw()){

            account.availableCreditLimit = account.availableCreditLimit.subtract(transaction.amount)

        }else{

            account.availableCreditLimit = account.availableCreditLimit.add(transaction.amount)
        }

        this.save(account)

    }
}
