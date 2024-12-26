package ma.mundia.digitalbankapp.Services;

import ma.mundia.digitalbankapp.Entities.BankAccount;
import ma.mundia.digitalbankapp.Entities.CurrentAccount;
import ma.mundia.digitalbankapp.Entities.SavingAccount;
import ma.mundia.digitalbankapp.Repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepository.findById("0ddfa803-5c99-45a2-b589-a2664d867a19").orElse(null);

        if (bankAccount != null) {
            System.out.println("******************************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());

            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over Draft : " + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate : " + ((SavingAccount) bankAccount).getInterestRate());
            }

            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println("************************Operations*********************");
                System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
            });
        }
    }
}
