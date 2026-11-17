//only for last instalment 
/**
package erp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import erp.core.exception.*; 

public class Basket implements Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    private final Client _client;
    private final Company _company;
    private final Map<Integer, BasketItem> _items = new TreeMap<>();
    private SplitStrategy _splitStrategy = new SingleOrderStrategy(); // UNICA (a criar)
    private ShippingRate _modality;                                   // null = sem modalidade
    private final List<ExtraService> _services = new ArrayList<>();

    public Basket(Client client, Company company) {
        _client = client;
        _company = company;
    }

    // ---------- items ----------
    public int update(Product product, int quantity)
            throws UnsuppliedProductException, ProductNotInBasketException,
                   InsufficientInventoryException {

        SuppliedProduct supplied = _company.getSuppliedProduct(product); // null se não disponibiliza
        if (supplied == null)
            throw new UnsuppliedProductException();

        BasketItem item = _items.get(product.getId());

        if (item == null) {
            if (quantity <= 0)
                throw new ProductNotInBasketException();
            if (quantity > supplied.getUnits())
                throw new InsufficientInventoryException();
            _items.put(product.getId(), new BasketItem(supplied, quantity));
            return quantity;
        }

        int total = item.getQuantity() + quantity;
        if (quantity > 0 && total > supplied.getUnits())
            throw new InsufficientInventoryException();
        if (total <= 0) {
            _items.remove(product.getId());
            return 0;
        }
        item.update(quantity);
        return total;
    }

    public double getItemsCost() {
        double total = 0;
        for (BasketItem item : _items.values())
            total += item.getCost();
        return total;
    }

    // ---------- opções da compra ----------

    public void setSplitStrategy(SplitStrategy strategy) { _splitStrategy = strategy; }
    public void setModality(ShippingRate modality) { _modality = modality; }
    public void addService(ExtraService service) { _services.add(service); }

    public boolean canCheckout() {
        return !_items.isEmpty() && _modality != null;
    }

    // ---------- apresentação ----------

    private String transportDescription() {
        if (_modality == null)
            return "SEM_TRANSPORTE";
        StringBuilder sb = new StringBuilder(_modality.getName());
        for (int i = _services.size() - 1; i >= 0; i--) // ordem inversa da adição
            sb.append('+').append(_services.get(i).getKey());
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_client.getEmail()).append(" - ")
          .append(_company.getId()).append(" - ")
          .append(_splitStrategy).append(" - ")        // toString da estratégia = UNICA / PRAZO
          .append(transportDescription()).append(" - ")
          .append(Money.format(getItemsCost()));
        for (BasketItem item : _items.values())
            sb.append("\n  ").append(item);
        return sb.toString();
    }

    // ---------- checkout (esqueleto; completa quando tiveres Purchase/Order) ----------

    Purchase checkout(int currentDate, int purchaseId)
            throws CannotCheckoutException, InsufficientInventoryException {

        if (!canCheckout())
            throw new CannotCheckoutException();

        // 1. Validar TUDO antes de alterar o que quer que seja (falha sem efeitos)
        for (BasketItem item : _items.values())
            if (item.getQuantity() > item.getSupplied().getUnits())
                throw new InsufficientInventoryException();

        // 2. Fixar os preços: criar as linhas da compra com item.getUnitPrice() actual
        // 3. Dividir em encomendas: _splitStrategy aplicada às linhas
        // 4. A cada encomenda: _modality e _services
        // 5. Construir a Purchase (data, id, cliente, empresa, encomendas)
        // 6. Efeitos: diminuir stock na empresa; _client.addSpent(custo total da compra)
        // 7. Esvaziar o cabaz:
        //      _items.clear(); _modality = null; _services.clear();
        //      _splitStrategy = new SingleOrderStrategy();
        // return purchase;
        throw new UnsupportedOperationException("TODO");
    }
}
*/