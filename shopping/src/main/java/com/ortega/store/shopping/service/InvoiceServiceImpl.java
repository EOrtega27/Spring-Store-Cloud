package com.ortega.store.shopping.service;

import com.ortega.store.shopping.client.CustomerClient;
import com.ortega.store.shopping.client.ProductClient;
import com.ortega.store.shopping.entity.Invoice;
import com.ortega.store.shopping.entity.InvoiceItem;
import com.ortega.store.shopping.model.Customer;
import com.ortega.store.shopping.model.Product;
import com.ortega.store.shopping.repository.InvoiceItemRepository;
import com.ortega.store.shopping.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemRepository invoiceItemsRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    ProductClient productClient;

    @Override
    public List<Invoice> findInvoiceAll() {
        return  invoiceRepository.findAll();
    }


    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState("CREATED");
        invoiceDB = invoiceRepository.save(invoice);
        invoiceDB.getItems().forEach(
                invoiceItem -> {
                    productClient.updateStockProduct( invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
                }
        );
        return invoiceDB;
    }


    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }


    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        Invoice invoiceDB = invoiceRepository.findById(id).orElse(null);
        if(invoiceDB != null){
            Customer customer = customerClient.getCustomer(invoiceDB.getCustomerId()).getBody();
            invoiceDB.setCustomer(customer);
            //Recoge los datos de los productos usando productClient
            List<InvoiceItem> lstItem = invoiceDB.getItems().stream().map(invoiceItem -> {
                Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
                invoiceItem.setProduct(product);
                return invoiceItem;
            }).collect(Collectors.toList());
            invoiceDB.setItems(lstItem);
        }
        return invoiceDB;
    }
}
