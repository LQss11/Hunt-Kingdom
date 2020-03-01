<?php

namespace ProduitBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Produit
 *
 * @ORM\Table(name="produit")
 * @ORM\Entity(repositoryClass="ProduitBundle\Repository\ProduitRepository")
 */
class Produit
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;
    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     */
    private $nom;

    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer")
     */
    private $quantite;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float")
     */
    private $prix;

    /**
     * @var int
     *
     * @ORM\Column(name="idFournisseur", type="integer", nullable=true)
     */
    private $idFournisseur;

    /**
     * @var int
     *
     * @ORM\Column(name="etatPromo", type="integer")
     */
    private $etatPromo;

    /**
     * @var int
     *
     * @ORM\Column(name="garantie", type="integer")
     */
    private $garantie;

    /**
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumn(name="categorie", referencedColumnName="id")
     */
    private $categorie;
    /**
     * @var string
     * @Assert\File(mimeTypes={ "image/jpeg", "image/png"})
     * @ORM\Column(name="image", type="string")
     */
    private $image;

    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Produit
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set quantite
     *
     * @param integer $quantite
     *
     * @return Produit
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;

        return $this;
    }

    /**
     * Get quantite
     *
     * @return integer
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Produit
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return Produit
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * Set idFournisseur
     *
     * @param integer $idFournisseur
     *
     * @return Produit
     */
    public function setIdFournisseur($idFournisseur)
    {
        $this->idFournisseur = $idFournisseur;

        return $this;
    }

    /**
     * Get idFournisseur
     *
     * @return integer
     */
    public function getIdFournisseur()
    {
        return $this->idFournisseur;
    }

    /**
     * Set etatPromo
     *
     * @param integer $etatPromo
     *
     * @return Produit
     */
    public function setEtatPromo($etatPromo)
    {
        $this->etatPromo = $etatPromo;

        return $this;
    }

    /**
     * Get etatPromo
     *
     * @return integer
     */
    public function getEtatPromo()
    {
        return $this->etatPromo;
    }

    /**
     * Set garantie
     *
     * @param integer $garantie
     *
     * @return Produit
     */
    public function setGarantie($garantie)
    {
        $this->garantie = $garantie;

        return $this;
    }

    /**
     * Get garantie
     *
     * @return integer
     */
    public function getGarantie()
    {
        return $this->garantie;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return Produit
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set categorie
     *
     * @param \ProduitBundle\Entity\Categorie $categorie
     *
     * @return Produit
     */
    public function setCategorie(\ProduitBundle\Entity\Categorie $categorie = null)
    {
        $this->categorie = $categorie;

        return $this;
    }

    /**
     * Get categorie
     *
     * @return \ProduitBundle\Entity\Categorie
     */
    public function getCategorie()
    {
        return $this->categorie;
    }
}
