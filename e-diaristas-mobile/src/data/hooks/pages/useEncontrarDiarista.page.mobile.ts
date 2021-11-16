import {useEffect, useState} from "react";
import * as Location from 'expo-location';

export default function useEncontrarDiarista() {
    const [cepAutomatico, setCepAutomatico] = useState(''),
        [coordendas, setCoordendas] = useState<{
            latitude: number;
            longitude: number;
        }>();

    useEffect(() => {
        (async () => {
            try {
                const gpsPermitido = await pedirPermissao();
                if (gpsPermitido) {
                    setCoordendas(await pegarCoordenadas());
                }
            } catch (error) {}
        })()
    }, []);

    useEffect(() => {
        (async () => {
            try {
                if (coordendas) {
                    setCepAutomatico(await pegarCep());
                }
            } catch (error) {}
        })()
    }, [coordendas]);

    async function pedirPermissao(): Promise<boolean> {
        try {
            const {status} = await Location.requestForegroundPermissionsAsync();
            return status === 'granted';
        } catch (error) {
            return false;
        }
    };

    async function pegarCoordenadas(): Promise<{
        latitude: number;
        longitude: number;
    }> {
        const localizacao = await Location.getCurrentPositionAsync({
            accuracy: Location.Accuracy.Highest
        });

        return localizacao.coords;
    };

    async function pegarCep(): Promise<string> {
        if (coordendas) {
            const endereco = await Location.reverseGeocodeAsync(coordendas);
            if (endereco.length > 0) {
                return endereco[0].postalCode || '';
            }
        }
        return '';
    }

    return {
        cepAutomatico,
    };
}
