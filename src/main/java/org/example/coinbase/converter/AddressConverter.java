package org.example.coinbase.converter;

import org.example.coinbase.dto.ListAddressesDto;
import org.example.coinbase.model.AddressModel;

import java.util.List;

public class AddressConverter {
    public static AddressModel addressConverter(List<ListAddressesDto.Address> data) {
        for (ListAddressesDto.Address address : data) {
            if (address.getAddress_info().getDestination_tag() != null) {
                return new AddressModel(
                        address.getAddress(),
                        address.getNetwork().toUpperCase().replace(" ",""),
                        address.getAddress_info().getDestination_tag()
                );
            } else {
                return new AddressModel(
                        address.getAddress(),
                        address.getNetwork().toUpperCase().replace(" ",""),
                        ""
                );
            }
        }
        return null;
    }
}
