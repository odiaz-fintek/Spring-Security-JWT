package com.auth.jwt.app.controller.BasicControllers;

import com.auth.jwt.app.entity.Account;
import com.auth.jwt.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/basic/accounts")
public class AccountController_basic {

    private final AccountService accountService;

    @Autowired
    public AccountController_basic(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable String accountId) {
        Optional<Account> account = accountService.findById(accountId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createacc")
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable String accountId, @RequestBody Account accountDetails) {
        Optional<Account> accountOptional = accountService.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            // Update the fields of the account with accountDetails here
            account.setType(accountDetails.getType());
            account.setAccount(accountDetails.getAccount());
            account.setDivision(accountDetails.getDivision());
            account.setSicCode(accountDetails.getSicCode());
            account.setParentId(accountDetails.getParentId());
            account.setDescription(accountDetails.getDescription());
            account.setAddressId(accountDetails.getAddressId());
            account.setShippingId(accountDetails.getShippingId());
            account.setRegion(accountDetails.getRegion());
            account.setMainPhone(accountDetails.getMainPhone());
            account.setAlternatePhone(accountDetails.getAlternatePhone());
            account.setFax(accountDetails.getFax());
            account.setTollFree(accountDetails.getTollFree());
            account.setTollFree2(accountDetails.getTollFree2());
            account.setOtherPhone1(accountDetails.getOtherPhone1());
            account.setOtherPhone2(accountDetails.getOtherPhone2());
            account.setOtherPhone3(accountDetails.getOtherPhone3());
            account.setEmail(accountDetails.getEmail());
            account.setEmailType(accountDetails.getEmailType());
            account.setWebAddress(accountDetails.getWebAddress());
            account.setSecCodeId(accountDetails.getSecCodeId());
            account.setRevenue(accountDetails.getRevenue());
            account.setEmployees(accountDetails.getEmployees());
            account.setIndustry(accountDetails.getIndustry());
            account.setCreditRating(accountDetails.getCreditRating());
            account.setNotes(accountDetails.getNotes());
            account.setStatus(accountDetails.getStatus());
            account.setAccountManagerId(accountDetails.getAccountManagerId());
            account.setRegionalManagerId(accountDetails.getRegionalManagerId());
            account.setDivisionalManagerId(accountDetails.getDivisionalManagerId());
            account.setNationalAccount(accountDetails.getNationalAccount());
            account.setTargetAccount(accountDetails.getTargetAccount());
            account.setTerritory(accountDetails.getTerritory());
            account.setCreateUser(accountDetails.getCreateUser());
            account.setModifyUser(accountDetails.getModifyUser());
            account.setCreateDate(accountDetails.getCreateDate());
            account.setModifyDate(accountDetails.getModifyDate());
            account.setAccountUc(accountDetails.getAccountUc());
            account.setAka(accountDetails.getAka());
            account.setCurrencyCode(accountDetails.getCurrencyCode());
            account.setInternalAccountNo(accountDetails.getInternalAccountNo());
            account.setExternalAccountNo(accountDetails.getExternalAccountNo());
            account.setParentAccountNo(accountDetails.getParentAccountNo());
            account.setAlternateKeyPrefix(accountDetails.getAlternateKeyPrefix());
            account.setAlternateKeySuffix(accountDetails.getAlternateKeySuffix());
            account.setDefaultTicketSecCodeId(accountDetails.getDefaultTicketSecCodeId());
            account.setNotifyDefects(accountDetails.getNotifyDefects());
            account.setNotifyOnClose(accountDetails.getNotifyOnClose());
            account.setNotifyOnStatus(accountDetails.getNotifyOnStatus());
            account.setShortNotes(accountDetails.getShortNotes());
            account.setUserField1(accountDetails.getUserField1());
            account.setUserField2(accountDetails.getUserField2());
            account.setUserField3(accountDetails.getUserField3());
            account.setUserField4(accountDetails.getUserField4());
            account.setUserField5(accountDetails.getUserField5());
            account.setUserField6(accountDetails.getUserField6());
            account.setUserField7(accountDetails.getUserField7());
            account.setUserField8(accountDetails.getUserField8());
            account.setUserField9(accountDetails.getUserField9());
            account.setUserField10(accountDetails.getUserField10());
            account.setCampaignId(accountDetails.getCampaignId());
            account.setDoNotSolicit(accountDetails.getDoNotSolicit());
            account.setScore(accountDetails.getScore());
            account.setTicker(accountDetails.getTicker());
            account.setSubtype(accountDetails.getSubtype());
            account.setLeadSourceId(accountDetails.getLeadSourceId());
            account.setImportSource(accountDetails.getImportSource());
            account.setEngineerId(accountDetails.getEngineerId());
            account.setSalesEngineerId(accountDetails.getSalesEngineerId());
            account.setRelationship(accountDetails.getRelationship());
            account.setLastHistoryBy(accountDetails.getLastHistoryBy());
            account.setLastHistoryDate(accountDetails.getLastHistoryDate());
            account.setBusinessDescription(accountDetails.getBusinessDescription());
            account.setWebAddress2(accountDetails.getWebAddress2());
            account.setWebAddress3(accountDetails.getWebAddress3());
            account.setWebAddress4(accountDetails.getWebAddress4());
            account.setGlobalSyncId(accountDetails.getGlobalSyncId());
            account.setAppId(accountDetails.getAppId());
            account.setTick(accountDetails.getTick());
            account.setLastErpSyncUpdate(accountDetails.getLastErpSyncUpdate());
            account.setPrimaryOperatingCompId(accountDetails.getPrimaryOperatingCompId());
            account.setPromotedToAccounting(accountDetails.getPromotedToAccounting());
            account.setCreateSource(accountDetails.getCreateSource());

            final Account updatedAccount = accountService.save(account);
            return ResponseEntity.ok(updatedAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountId) {
        accountService.deleteById(accountId);
        return ResponseEntity.ok().build();
    }
}